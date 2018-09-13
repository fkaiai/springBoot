package cn.fk.te.zk;

import org.apache.commons.lang3.RandomUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@RestController
public class RestBizController {

    final CountDownLatch latch = new CountDownLatch(10);

    @RequestMapping(value = "/zkget" ,method = RequestMethod.GET)
    public String aa() throws InterruptedException, IOException, KeeperException {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DistributedLock lock = null;
                    try {
                        lock = new DistributedLock();
                        latch.countDown();
                        latch.await();
                        lock.lock();
                        Thread.sleep(12000);
                    }  catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock != null) {
                            lock.unlock();
                        }
                    }
                }
            }).start();
        }
        return "aa";
    }
}
