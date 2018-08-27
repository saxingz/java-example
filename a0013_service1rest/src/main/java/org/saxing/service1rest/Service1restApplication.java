package org.saxing.service1rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class Service1restApplication {

    public static void main(String[] args) {
        SpringApplication.run(Service1restApplication.class, args);
    }

    @RequestMapping("/hi")
    public String home() {
        return "Hello , service1rest 8083";
    }

}
