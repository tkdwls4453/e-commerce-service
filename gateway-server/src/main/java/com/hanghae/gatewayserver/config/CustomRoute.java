package com.hanghae.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRoute {

    @Bean
    public RouteLocator cRoute(RouteLocatorBuilder builder) {

        return builder.routes()
            .route(p -> p.path("/users/**", "/internal/users/**")
                .uri("lb://user"))
            .route(p -> p.path("/auth/**", "/internal/auth/**")
                .uri("lb://user"))
            .route(p -> p.path("/products/**", "/internal/products/**")
                .uri("lb://product"))
            .route(p -> p.path("/orders/**", "/internal/orders/**")
                .uri("lb://order"))
            .route(p -> p.path("/payments/**", "/internal/payments/**")
                .uri("lb://payment"))
            .build();
    }
}
