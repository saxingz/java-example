package org.saxing.a0023_search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.saxing.a0023_search.mapper")
public class A0023SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(A0023SearchApplication.class, args);
    }

}

