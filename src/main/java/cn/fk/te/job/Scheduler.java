package cn.fk.te.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class Scheduler {

//    @Scheduled(cron="2/2 * * * * ?")
    public void job1(){
        Calendar c = Calendar.getInstance();
        int minute = c.get(Calendar.MINUTE);
        if(minute==41){


            return;
        }
        System.out.println(minute);


    }
}
