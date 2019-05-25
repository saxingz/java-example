package org.saxing.aa0029_03_test_demo;

import org.saxing.aa0029_02_test_greet.GreetingApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Aa002903TestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Aa002903TestDemoApplication.class, args);
    }

    @Bean
    public GreetingApplicationRunner greetingApplicationRunner(){
        return new GreetingApplicationRunner("modify self");
    }

}
