package com.hanghae.product.application;

import static org.junit.jupiter.api.Assertions.*;

import com.hanghae.product.exception.InsufficientStockException;
import com.hanghae.product.presentation.dto.request.ReduceStockRequest.Info;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    @BeforeEach
    public void setUp() {
        // 초기 재고 설정
        redisTemplate.opsForValue().set("item1:stock", 1000);
        redisTemplate.opsForValue().set("item2:stock", 1000);
    }

    @Test
    public void testReduceStockConcurrency() throws InterruptedException, ExecutionException {
        int numThreads = 100; // 스레드 수를 크게 설정
        int numRequests = 500; // 요청 횟수도 증가
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < numRequests; i++) {
            tasks.add(() -> {
                try {
                    List<Info> infos = List.of(new Info(1L, 1), new Info(2L, 1)); // 각 호출당 아이템 1, 2의 재고 1씩 감소 요청
                    itemService.reduceStock(infos);
                    return "Success";
                } catch (InsufficientStockException e) {
                    return "Insufficient Stock";
                } catch (Exception e) {
                    return "Error";
                }
            });
        }

        List<Future<String>> results = executorService.invokeAll(tasks);
        executorService.shutdown();

        long successCount = results.stream().filter(f -> {
            try {
                return "Success".equals(f.get());
            } catch (Exception e) {
                return false;
            }
        }).count();

        // 기대 결과: 재고가 정확하게 감소하여 모든 요청 성공
        int expectedFinalStock = 1000 - numRequests;
        assertEquals(expectedFinalStock, redisTemplate.opsForValue().get("item1:stock"));
        assertEquals(expectedFinalStock, redisTemplate.opsForValue().get("item2:stock"));

        // 테스트 결과 출력
        System.out.println("Successful reductions: " + successCount);
        System.out.println("Final stock for item 1: " + redisTemplate.opsForValue().get("item1:stock"));
        System.out.println("Final stock for item 2: " + redisTemplate.opsForValue().get("item2:stock"));
    }
}