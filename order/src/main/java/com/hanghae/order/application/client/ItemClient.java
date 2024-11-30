package com.hanghae.order.application.client;

import ch.qos.logback.core.spi.ConfigurationEvent;
import com.hanghae.common.response.CustomResponse;
import com.hanghae.order.application.client.request.ReduceStockRequest;
import com.hanghae.order.application.client.response.ItemProductResponse;
import com.hanghae.order.domain.dto.request.OrderCreateDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product", url = "http://localhost:8080/internal/products/items")
public interface ItemClient {

//    @GetMapping
//    CustomResponse<List<ItemProductResponse>> getItemProducts(@RequestParam("itemIds") List<Long> itemIds);

    @GetMapping("/{itemId}")
    CustomResponse<ItemProductResponse> getItemProduct(@PathVariable(name = "itemId") Long itemId);

    @PostMapping("/deduct")
    CustomResponse<String> reduceStock(@RequestBody ReduceStockRequest request);

    @PostMapping("/deduct")
    CustomResponse<List<ItemProductResponse>> reduceStockAndGetInfo(@RequestBody ReduceStockRequest request);
}
