package cn.fk.te;

import cn.fk.te.service.UserService;
import cn.fk.te.thread.Thread1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeApplicationTests {

	@Autowired
	UserService userService;

	@Test
	@Transactional
	public void test123() throws InterruptedException {
		System.out.println(Thread.currentThread().getName()+"主线程运行开始!");
		Thread1 mTh1=new Thread1("A");
		Thread1 mTh2=new Thread1("B");
		mTh1.start();
		mTh2.start();
		mTh1.join();
		mTh2.join();
		System.out.println(Thread.currentThread().getName()+ "主线程运行结束!");
	}

}
