package cn.fk.te;

import cn.fk.te.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeApplicationTests {

	@Autowired
	UserMapper userMapper;

	@Test
	public void test123() {
		System.out.println(userMapper.selectByPrimaryKey(16).toString());
	}

}
