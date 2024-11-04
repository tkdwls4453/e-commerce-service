package com.hanghae.order.application.client;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.order.application.client.response.ItemProductResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product", url = "http://localhost:8080/api/products/items")
public interface ItemClient {

    @GetMapping
    CustomResponse<List<ItemProductResponse>> getItemProducts(@RequestParam("itemIds") List<Long> itemIds);
}