package cn.fk.te.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class testController {

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public void test1(HttpServletRequest request){

        Myrunnable myrunnable =new Myrunnable();
        Thread th1=new Thread(myrunnable,"线程1");
        Thread th2=new Thread(myrunnable,"线程2");
        Thread th3=new Thread(myrunnable,"线程3");
        th1.start();
        th2.start();
        th3.start();
    }

    class Myrunnable implements Runnable{

        int count=10;

        @Override
        public void run() {
            while(count>0){
                count--;
                System.out.println(Thread.currentThread().getName()+"卖了一张票，剩"+count);
            }
        }
    }
}
