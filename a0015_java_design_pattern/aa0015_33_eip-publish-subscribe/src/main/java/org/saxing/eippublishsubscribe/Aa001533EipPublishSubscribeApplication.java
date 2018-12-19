package org.saxing.eippublishsubscribe;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 * 
 * @author saxing 2018/12/19 20:03
 */
public class Aa001533EipPublishSubscribeApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001533EipPublishSubscribeApplication.class);

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:origin").multicast().to("mock:foo", "mock:bar", "stream:out");
            }
        });

        ProducerTemplate template = context.createProducerTemplate();
        context.start();
        context.getRoutes().forEach(r -> LOGGER.info(r.toString()));
        template.sendBody("direct:origin", "Hello from origin");
        context.stop();
    }

}

