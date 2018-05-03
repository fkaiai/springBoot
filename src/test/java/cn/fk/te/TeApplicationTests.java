package cn.fk.te;

import cn.fk.te.entity.User;
import cn.fk.te.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeApplicationTests {

	@Autowired
	UserService userService;

	@Test
//	@Transactional
	public void test123() throws InterruptedException {
		User user = new User();
		user.setUsername("asdf");
		userService.insert(user);

	}

}
