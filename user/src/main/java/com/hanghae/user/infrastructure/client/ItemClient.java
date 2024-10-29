package com.hanghae.user.infrastructure.client;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.user.domain.dto.response.ItemInfo;
import com.hanghae.user.infrastructure.client.request.GetItemProductRequest;
import com.hanghae.user.infrastructure.client.response.ItemProductResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product", url = "http://localhost:8080/api/products/items")
public interface ItemClient {

    @GetMapping("/{itemId}")
    CustomResponse<ItemInfo> getItemInfo(@PathVariable("itemId") Long id);

    @GetMapping
    CustomResponse<List<ItemProductResponse>> getItemProducts(@RequestParam("itemIds") List<Long> itemIds);
}
