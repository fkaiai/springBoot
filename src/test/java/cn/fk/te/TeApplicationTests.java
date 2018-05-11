package cn.fk.te;

import cn.fk.te.entity.User;
import cn.fk.te.service.UserService;

import com.alibaba.fastjson.JSON;
import org.json.JSONObject;
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
	public void test123() {
		String aa="{\n" +
				"    \"firstName\": \"John\",\n" +
				"    \"lastName\": \"Smith\",\n" +
				"    \"age\": 25,\n" +
				"    \"address\": {\n" +
				"        \"streetAddress\": \"21 2nd Street\",\n" +
				"        \"city\": \"New York\",\n" +
				"        \"state\": \"NY\",\n" +
				"        \"postalCode\": 10021\n" +
				"    },\n" +
				"    \"phoneNumbers\": [\n" +
				"        {\n" +
				"            \"type\": \"home\",\n" +
				"            \"number\": \"212 555-1234\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"type\": \"fax\",\n" +
				"            \"number\": \"646 555-4567\" \n" +
				"        }\n" +
				"    ] \n" +
				"}";




	}

}
