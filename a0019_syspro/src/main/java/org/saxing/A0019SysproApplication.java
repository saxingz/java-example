package org.saxing;

import org.saxing.syspro.GetPro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class A0019SysproApplication {

    public static void main(String[] args) {

        GetPro.Config();
        GetPro.getConfig();

        SpringApplication.run(A0019SysproApplication.class, args);

    }
}
