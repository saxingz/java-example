package org.saxing.eipsplitter.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * SplitterRoute
 * 
 * @author saxing 2018/12/20 23:37
 */
@Component
public class SplitterRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("{{entry}}").split().body().to("{{endpoint}}");
    }
}
