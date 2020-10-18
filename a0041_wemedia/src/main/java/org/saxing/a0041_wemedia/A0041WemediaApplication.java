package org.saxing.a0041_wemedia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * wemedia
 *
 * @author saxing 2020/10/18 13:25
 */
@MapperScan("org.saxing.a0041_wemedia.dao")
@SpringBootApplication
public class A0041WemediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(A0041WemediaApplication.class, args);
    }

}
