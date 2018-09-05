package org.saxing.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class Router {

    @Autowired
    private HealthHandler healthHandler;

    @Bean
    public RouterFunction<?> routerFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/gateway/hi"),
                healthHandler::healthCheck);
    }

}
