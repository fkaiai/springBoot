package cn.fk.te;


import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
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
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.util.Hexadecimals.byteToHexString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test123() {
		Set<Integer> set = new HashSet<Integer>();

		set.add(5);
		set.add(1);
		set.add(4);
		System.out.println(set);



	}


	public static String md5(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				int i = byteDigest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			return buf.toString();
		}
		catch (NoSuchAlgorithmException e){

			e.printStackTrace();
		}
		return null;
	}


	public static String md51(String origin){
		String resultString = null;
		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(resultString.getBytes("UTF-8"));
//            resultString = byteArrayToHexString(md.digest());
			resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}


	//转换字节数组为16进制字串
	public static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (byte aB : b) {
			resultSb.append(StringUtil.byteToHexString(aB));
		}
		return resultSb.toString();
	}





}
