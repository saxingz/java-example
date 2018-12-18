package org.saxing.eipmessagechannel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author saxing 2018/12/18 22:15
 */
public class Aa001532EipMessageChannelApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001532EipMessageChannelApplication.class);

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("stream:in").to("direct:greetings");
                from("direct:greetings").to("stream:out");
            }
        });

        context.start();
        context.getRoutes().forEach(r -> LOGGER.info(r.toString()));
        context.stop();
    }

}

