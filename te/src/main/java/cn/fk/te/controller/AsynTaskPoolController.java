package cn.fk.te.controller;

import cn.fk.te.service.AsynTaskPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AsynTaskPoolController {

    @Autowired
    AsynTaskPool asynTaskPool;

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public void test1(HttpServletRequest request){

        for(int i=0;i<20;i++){
            Runnable runnable=new TaskWithoutResult(2000);
            asynTaskPool.excute(runnable);
        }

    }

    class TaskWithoutResult implements Runnable {
        private int sleepTime=1000;//默认睡眠时间1s
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
