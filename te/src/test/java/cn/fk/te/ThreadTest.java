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

        System.out.println(0);
    }




}


