package com.hanghae.gatewayserver.filter;

import com.hanghae.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationHeaderFilter implements GlobalFilter {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        String path = request.getPath().toString();
        String method = request.getMethod().toString();

        // 화이트리스트 URL 및 메소드 확인
        if (WhitelistUrls.isWhitelisted(method, path)) {
            return chain.filter(exchange); // 인증 확인 없이 다음 필터로 이동
        }

        if(!request.getHeaders().containsKey("Authorization")) {
            return onError(exchange, "헤더에 인증 토큰이 존재하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

        String authorizationHeader = request.getHeaders().getFirst("Authorization");
        String jwt = authorizationHeader = authorizationHeader.replace("Bearer ", "");

        if(jwtUtil.isExpired(jwt)){
            return onError(exchange, "토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

        // JWT에서 userId 추출 및 헤더에 추가
        String userId = String.valueOf(jwtUtil.getUserId(jwt));

        ServerHttpRequest modifiedRequest = request.mutate()
            .header("X-User-Id", userId)
            .build();

        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        log.error(message);
        return response.setComplete();
    }
}
