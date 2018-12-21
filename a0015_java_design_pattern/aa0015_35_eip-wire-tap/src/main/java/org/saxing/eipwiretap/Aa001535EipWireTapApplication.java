package org.saxing.eipwiretap;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * main
 * 
 * @author saxing 2018/12/21 23:40
 */
@SpringBootApplication
public class Aa001535EipWireTapApplication {

    public static void main(String[] args) throws Exception {
        // Run Spring Boot application and obtain ApplicationContext
        ConfigurableApplicationContext context = SpringApplication.run(Aa001535EipWireTapApplication.class, args);

        // Get CamelContext from ApplicationContext
        CamelContext camelContext = (CamelContext) context.getBean("camelContext");

        // Add a new routes that will handle endpoints form WireTapRoute class.
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("{{endpoint}}").log("ENDPOINT: ${body}");
                from("{{wireTapEndpoint}}").log("WIRETAPPED ENDPOINT: ${body}");
            }
        });

        // Add producer that will send test message to an entry point in WireTapRoute
        camelContext.createProducerTemplate().sendBody("{{entry}}", "Test message");

        SpringApplication.exit(context);

    }

}

