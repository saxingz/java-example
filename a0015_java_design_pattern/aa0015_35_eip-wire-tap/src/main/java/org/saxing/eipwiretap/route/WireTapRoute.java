package org.saxing.eipwiretap.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * WireTapRopute
 * 
 * @author saxing 2018/12/21 23:37
 */
@Component
public class WireTapRoute extends RouteBuilder {

    /**
     * Configures the route
     *
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        // Main route
        from("{{entry}}").wireTap("direct:wireTap").to("{{endpoint}}");

        // Wire tap route
        from("direct:wireTap").log("Message: ${body}").to("{{wireTapEndpoint}}");
    }
}
