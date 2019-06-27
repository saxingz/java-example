package org.saxing.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

//@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class A0016CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(A0016CloudGatewayApplication.class, args);
    }
}
