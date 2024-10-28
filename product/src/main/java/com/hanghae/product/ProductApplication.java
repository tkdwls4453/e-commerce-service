package com.hanghae.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.hanghae.product", "com.hanghae.common"})
public class ProductApplication {
    public static void main(String[] args){
        SpringApplication.run(ProductApplication.class, args);
    }
}
