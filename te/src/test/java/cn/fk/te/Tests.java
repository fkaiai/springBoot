package cn.fk.te;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.util.Hexadecimals.byteToHexString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test123() {
		String encodeStr= DigestUtils.md5Hex("appid=wxd93613aa9b48b889&noncestr=1J7VOOPLXZ6563DOCVYQW3VG5ZUL2K91&package=Sign=WXPay&partnerid=1401854202&prepayid=wx09161954351867474a113b4e3821290560&timeStamp=1541751594&key=A8B47FA3640EE67CABF779EECB0CEA09");
		System.out.println(encodeStr);


	}





}
