package cn.fk.te.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RestBizController {

    @RequestMapping(value = "/zkget" ,method = RequestMethod.GET)
    public String aa() throws InterruptedException, IOException, KeeperException {
        DistributedLock lock = null;
        lock = new DistributedLock();
        lock.lock();
        Thread.sleep(5000);
        lock.unlock();
        return "aa";
    }
}
