package cn.fk.te;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test123() {

		//ResponseEntity<String> res =restTemplate.getForEntity("http://sitccm.maimaiti.cn/ccm-api/actCredit/get?type=shenquan&size=3",String.class);
		//String res = restTemplate.getForObject("http://sitccm.maimaiti.cn/ccm-api/actCredit/get?type=shenquan&size=3", String.class);
		//JSONObject json=restTemplate.getForEntity("http://sitccm.maimaiti.cn/ccm-api/actCredit/get?type=shenquan&size=3",JSONObject.class).getBody();
		//System.out.println(json);

		/*String url = "http://sitccm.maimaiti.cn/ccm-push/recerive/toPushUser";
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		String requestJson = "[{\"batchName\":\"贷超20181030\",\"customerId\":\"13062078\",\"openId\":\"o_tBRuC5v0msqOSxIWuRCG4xUSvg\"}]";
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		JSONObject json=restTemplate.postForEntity(url,entity,JSONObject.class).getBody();
		System.out.println(json);*/


	}



}
