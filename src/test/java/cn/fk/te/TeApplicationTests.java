package cn.fk.te;

import cn.fk.te.service.UserService;
import cn.fk.te.utils.Pair;
import cn.fk.te.utils.http.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeApplicationTests {

	@Autowired
	UserService userService;

	@Test
	public void test123() {

	    Map<String,Object> map= new HashMap<String,Object>();
	    /*map.put("activityId","2018duanwufuli");
	    map.put("plateId","main");*/
	    map.put("mobilePhoneNo","15989494507");
        Pair<Integer,String> aa= HttpUtil.get("http://localhost:8088/user/testt",map);


        System.out.println(aa);



	}

}
