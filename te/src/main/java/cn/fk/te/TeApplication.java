package cn.fk.te;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@MapperScan("cn.fk.te.mapper")
public class TeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeApplication.class, args);
	}
}
