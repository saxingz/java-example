package org.saxing.eipsplitter;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * main
 *
 * @author saxing 2018/12/20 23:44
 */
@SpringBootApplication
public class Aa001534EipSplitterApplication {

    public static void main(String[] args) throws Exception {
        // Run Spring Boot application and obtain ApplicationContext
        ConfigurableApplicationContext context = SpringApplication.run(Aa001534EipSplitterApplication.class, args);

        // Get CamelContext from ApplicationContext
        CamelContext camelContext = (CamelContext) context.getBean("camelContext");

        // Add a new routes that will handle endpoints form SplitterRoute class.
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("{{endpoint}}").log("ENDPOINT: ${body}");
            }
        });

        // Add producer that will send test message to an entry point in WireTapRoute
        String[] stringArray = {"Test item #1", "Test item #2", "Test item #3"};
        camelContext.createProducerTemplate().sendBody("{{entry}}", stringArray);

        SpringApplication.exit(context);

    }

}

