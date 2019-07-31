package org.saxing.gateway2_test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class A0032Gateway2TestApplication {


    public static void main(String[] args) {
        SpringApplication.run(A0032Gateway2TestApplication.class, args);
    }

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("sessionkey"));
    }

//    @Bean
//    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
////        return new LettuceConnectionFactory(host, port);
//        return new LettuceConnectionFactory("192.168.30.229", 6379);
//    }
}
