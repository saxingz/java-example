package org.saxing.gateway.global;

import org.saxing.gateway.global.common.StringConstant;
import org.saxing.gateway.global.path.SkipPath;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * 校验token是否有效
 * 
 * @author 刘罕  2018/7/23 11:08
 */
//@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {

//    @Autowired
    private SkipPath skipPath;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (Arrays.asList(skipPath.getPath().split(StringConstant.SYMBOL_COMMA)).contains(path)){
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token == null || token.isEmpty()){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
