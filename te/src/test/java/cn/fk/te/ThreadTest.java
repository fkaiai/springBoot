package cn.fk.te;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest {

    @Test
    public void tt(){
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
            while (count>0){
                count--;
                System.out.println(Thread.currentThread().getName()+"卖了一张票，剩"+count);
            }
        }
    }

}


