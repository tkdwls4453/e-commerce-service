package com.hanghae.gatewayserver.filter;

import java.util.List;
import java.util.Map;

public class WhitelistUrls {

    // URL과 메소드(HTTP Method)를 키로 사용하여 화이트리스트를 설정
    private static final Map<String, List<String>> WHITELIST = Map.of(
        "GET", List.of("/internal"),
        "POST", List.of("/users", "/internal"),
        "PUT", List.of("/internal"),
        "DELETE", List.of("/internal")

    );

    // 메소드와 경로를 기반으로 화이트리스트에 포함된지 확인하는 메서드
    public static boolean isWhitelisted(String method, String path) {
        return WHITELIST.getOrDefault(method, List.of()).stream().anyMatch(path::startsWith);
    }

}
