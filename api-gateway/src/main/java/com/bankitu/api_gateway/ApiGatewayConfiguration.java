package com.bankitu.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r ->
                        r.path("/auth/**")
                                .uri("lb://AUTH-SERVICE")
                )
                .route("account-service", r ->
                        r.path("/account/**")
                                .uri("lb://ACCOUNT-SERVICE")
                )
                .route("user-service", r ->
                        r.path("/users/**")
                                .uri("lb://USER-SERVICE")
                )
                .route("transaction-service", r ->
                        r.path("/transaction/**")
                                .uri("lb://TRANSACTION-SERVICE")
                )
                .build();
    }
}
