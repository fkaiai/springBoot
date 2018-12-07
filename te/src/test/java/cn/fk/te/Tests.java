package cn.fk.te;


import cn.fk.te.product.mapper.ProductMapper;
import cn.fk.te.product.model.po.Product;
import cn.fk.te.product.service.impl.ProductServiceImpl;
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
	@Autowired
	private ProductServiceImpl productService;

	@Test
	public void test123() {

		/*Product product=new Product();
		product.setId(1);
		product.setLoanTimeLimit("7-21天");
		Integer aa=productService.update(product);*/


	}








}
