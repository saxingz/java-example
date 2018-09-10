package org.saxing.gateway.global.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 增加header 操作
 *
 * @author saxing
 *
 */
public class AddHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("authToken");
        //向headers中放文件，记得build
        ServerHttpRequest host = exchange.getRequest().mutate().header("a", "888").build();
        //将现在的request 变成 change对象
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);

    }

    @Override
    public int getOrder() {
        return -100;
    }
}
