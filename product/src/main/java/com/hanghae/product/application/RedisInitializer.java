package com.hanghae.product.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.product.application.port.ItemQueryRepository;
import com.hanghae.product.application.port.ProductQueryRepository;
import com.hanghae.product.domain.Item;
import com.hanghae.product.presentation.dto.response.ItemProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RedisInitializer {

    private final RedisTemplate<String, Integer> redisTemplateForStock;
    private final RedisTemplate<String, String> redisTemplateForInfo;
    private final ItemQueryRepository itemQueryRepository;
    private final ProductQueryRepository productQueryRepository;
    private final ObjectMapper objectMapper;

    @Bean
    public ApplicationRunner initializeInventoryRunner() {
        return args -> loadInitialInventory();
    }

    @Bean
    public ApplicationRunner initializeItemInfoRunner() {
        return args -> loadItemInfo();
    }

    @Transactional(readOnly = true)
    public void loadInitialInventory() {
        List<Item> items = itemQueryRepository.findAll();

        items.forEach(item -> {
            String key = "item" + item.getId() + ":stock";
            redisTemplateForStock.opsForValue().set(key, item.getStockQuantity());
        });
    }

    @Transactional(readOnly = true)
    public void loadItemInfo(){
        List<Item> items = itemQueryRepository.findAll();

        items.forEach(item ->{
            String key = "item" + item.getId() +":info";
            ItemProductResponse itemProductResponse = ItemProductResponse.builder()
                .itemId(item.getId())
                .price(item.getPrice())
                .productName("Product 1")
                .color(item.getColor())
                .description("Product 1 Description")
                .size(item.getSize())
                .build();

            try {
                String data = objectMapper.writeValueAsString(itemProductResponse);
                redisTemplateForInfo.opsForValue().set(key, data);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("JSON 직렬화 실패", e);
            }
        });
    }
}
