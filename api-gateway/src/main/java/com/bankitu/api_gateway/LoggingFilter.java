package com.bankitu.api_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Log Request
        log.info("Request Path: {}", exchange.getRequest().getPath());
        log.info("Request Method: {}", exchange.getRequest().getMethod());
        log.info("Request URI: {}", exchange.getRequest().getURI());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // Log Response Status Code
            log.info("Response Status Code: {}", exchange.getResponse().getStatusCode());
        }));
    }
}
