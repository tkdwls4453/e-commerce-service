package com.hanghae.product.application;

import com.hanghae.product.infrastructure.repository.ItemCommandJpaRepository;
import com.hanghae.product.infrastructure.repository.ItemJpaRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class StockSyncService {

    private final RedisTemplate<String, Integer> redisStockTemplate;
    private final ItemCommandJpaRepository itemCommandJpaRepository;

    @Scheduled(fixedRate = 300000)
    public void syncStockToDatabase(){
        System.out.println("Redis 재고 동기화 시작");
        Map<Long, Integer> stockInfo = getAllStock();

        for(Long itemId : stockInfo.keySet()){
            itemCommandJpaRepository.updateStockById(itemId, stockInfo.get(itemId));
        }

    }

    private Map<Long, Integer> getAllStock(){
        Map<Long, Integer> stock = new HashMap<>();
        Set<String> keys = redisStockTemplate.keys("item*:stock");

        if(keys != null){
            for(String key : keys){
                Integer value = redisStockTemplate.opsForValue().get(key);
                String name = key.split(":")[0];
                long itemId = Long.parseLong(name.replace("item", ""));

                stock.put(itemId, value);
            }
        }

        return stock;
    }
}
