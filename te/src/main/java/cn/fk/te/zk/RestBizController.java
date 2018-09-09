package cn.fk.te.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestBizController {

    @RequestMapping(value = "/zkget" ,method = RequestMethod.GET)
    public String aa() {
        Watcher watcher= new Watcher(){
            @Override
            public void process(WatchedEvent event) {
                System.out.println("receive event："+event);
            }
        };

        String value = null;
        try {
            final ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 999999, watcher);
            final byte[] data = zookeeper.getData("/aa", watcher, null);
            value = new String(data);
            zookeeper.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return "get value from zookeeper [" + value + "]";
    }
}
