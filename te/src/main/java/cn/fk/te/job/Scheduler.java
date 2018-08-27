package cn.fk.te.job;

import cn.fk.te.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Scheduler {

    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public static ConcurrentHashMap<String, String> TASK_MAP = new ConcurrentHashMap<>(32);

    @Scheduled(cron="0/10 * * * * ?")
    public void job1(){

//        threadPoolTaskScheduler.schedule(new task1(), DateUtil.parseLong("2018-08-19 19:17:00"));
        System.out.println(TASK_MAP);
        TASK_MAP.put("aa","bb");
        System.out.println(1);
    }

    class task1 implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            System.out.println(threadPoolTaskScheduler.getScheduledExecutor());
        }
    }


}
