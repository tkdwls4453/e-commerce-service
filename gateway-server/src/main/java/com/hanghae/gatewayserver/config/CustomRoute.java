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
            .route(p -> p.path("/api/users/**")
                .uri("lb://user"))
            .route(p-> p.path("/api/auth/**")
                .uri("lb://user"))
            .route(p-> p.path("/api/products/**")
                .uri("lb://product"))
            .route(p-> p.path("/api/orders/**")
                .uri("lb://order"))
            .build();
    }
}
