package cn.fk.te;

import cn.fk.te.service.AsynTaskPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest {

    @Autowired
    AsynTaskPool asynTaskPool;

    @Test
    public void tt(){

        for(int i=0;i<1000;i++){
            Runnable runnable=new TaskWithoutResult(1000);
            asynTaskPool.excute(runnable);
        }
    }


    class TaskWithoutResult implements Runnable {
        private int sleepTime=500;//默认睡眠时间1s
        public TaskWithoutResult(int sleepTime){
            this.sleepTime=sleepTime;
        }
        @Override
        public void run(){
            System.out.println("线程"+Thread.currentThread()+"开始运行");
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {//捕捉中断异常

                System.out.println("线程"+Thread.currentThread()+"被中断");
            }
            System.out.println("线程"+Thread.currentThread()+"结束运行");
        }
    }


}


