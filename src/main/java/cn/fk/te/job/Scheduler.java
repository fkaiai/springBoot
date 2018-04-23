package cn.fk.te.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

//    @Scheduled(cron="2/2 * * * * ?")
    public void job1(){
        System.out.println(2);
    }
}
