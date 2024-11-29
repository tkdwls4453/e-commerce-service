package com.hanghae.order.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

@Slf4j
@Configuration
public class LuaScriptConfig {

    @Bean
    public RedisScript<String> stockDecreaseScript() throws IOException {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        Resource scriptResource = new ClassPathResource("scripts/stockDecrease.lua");

        // Lua 스크립트 파일 로드 확인
        try {
            String scriptText = Files.readString(Path.of(scriptResource.getURI()));
            log.info("Lua script loaded successfully:\n" + scriptText);
            redisScript.setScriptText(scriptText);
        } catch (IOException e) {
            log.error("Failed to load Lua script from: " + scriptResource.getURI().getPath(), e);
            throw new RuntimeException("Could not load Lua script", e);  // 예외를 던져 애플리케이션 초기화가 실패하게 함
        }

        redisScript.setResultType(String.class);
        return redisScript;
    }

}