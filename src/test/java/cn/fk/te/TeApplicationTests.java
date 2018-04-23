package cn.fk.te;

import cn.fk.te.entity.User;
import cn.fk.te.mapper.UserMapper;
import cn.fk.te.service.UserService;
import cn.fk.te.service.impl.UserServiceImpl;
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
//	@Transactional
	public void test123() {
//		System.out.println(userMapper.selectByPrimaryKey(16).toString());

		User user = new User();
		user.setUsername("qqq");
		userService.insert(user);
		Integer aa=4/0;
//		System.out.println(aa);
		userService.insert(user);
	}

}
