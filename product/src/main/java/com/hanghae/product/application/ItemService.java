package com.hanghae.product.application;

import com.hanghae.product.application.port.ItemCommandRepository;
import com.hanghae.product.application.port.ItemQueryRepository;
import com.hanghae.product.domain.dto.response.ItemProductDto;
import com.hanghae.product.exception.InsufficientStockException;
import com.hanghae.product.presentation.dto.request.ReduceStockRequest.Info;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemQueryRepository itemQueryRepository;
    private final ItemCommandRepository itemCommandRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisTemplate<String, Integer> redisStockTemplate;
    private final RedisScript<String> stockDecreaseScript;

    @Transactional(readOnly = true)
    public List<ItemProductDto> getItemProducts(List<Long> itemIds) {
        return itemQueryRepository.getItemProducts(itemIds);
    }

    public void reduceStock(List<Info> infos) throws IOException {


        // Redis KEYS (아이템별 재고 키 리스트)
        List<String> keys = infos.stream()
            .map(info -> "item" + info.getItemId() + ":stock")
            .toList();

        // Redis ARGV (아이템별 감소할 수량 리스트)
        List<String> args = infos.stream()
            .map(info -> String.valueOf(info.getQuantity()))
            .toList();

        // Lua 스크립트 실행 및 결과 확인
        String result = redisTemplate.execute(stockDecreaseScript, keys, args.toArray());

        System.out.println("[test]" + result);

        // 결과가 에러 메시지인 경우 처리
        if (result != null && result.contains("err")) {
            throw new RuntimeException("Redis error: " + result);
        }

        if (result == null || !result.equals("All items updated successfully")) {
            throw new InsufficientStockException();
        }

        System.out.println("Success: " + result);

    }

    public void restoreStock(List<Info> infos){
        for(Info info : infos) {
            String key = "item" + info.getItemId() + ":stock";
            Integer stock = itemQueryRepository.getStock(info.getItemId());

            redisStockTemplate.opsForValue().increment(key, info.getQuantity());
        }
    }
}
