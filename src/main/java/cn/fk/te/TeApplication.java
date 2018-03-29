package cn.fk.te;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("cn.fk.te.mapper")
public class TeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeApplication.class, args);
	}
}
