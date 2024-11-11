package com.hanghae.product.application;

import com.hanghae.product.application.port.ItemQueryRepository;
import com.hanghae.product.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryInitializer {

    private final RedisTemplate<String, Integer> redisTemplate;
    private final ItemQueryRepository itemQueryRepository;

    @Bean
    public ApplicationRunner initializeInventoryRunner() {
        return args -> loadInitialInventory();
    }

    @Transactional(readOnly = true)
    public void loadInitialInventory() {
        List<Item> items = itemQueryRepository.findAll();

        items.forEach(item -> {
            String key = "item" + item.getId() + ":stock";
            redisTemplate.opsForValue().set(key, item.getStockQuantity());
        });
    }
}
