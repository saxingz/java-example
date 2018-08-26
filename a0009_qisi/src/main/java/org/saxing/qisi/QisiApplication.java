package org.saxing.qisi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@MapperScan("org.saxing.qisi.dao.mapper")
public class QisiApplication{

	public static void main(String[] args) {
		SpringApplication.run(QisiApplication.class, args);
	}

}
