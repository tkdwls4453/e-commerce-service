package com.hanghae.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableFeignClients
@EnableWebSecurity
@SpringBootApplication
@ComponentScan(basePackages = {"com.hanghae.user", "com.hanghae.common", "com.hanghae.jwt"})
public class UserApplication {

    public static void main(String[] args){
        SpringApplication.run(UserApplication.class, args);
    }
}
