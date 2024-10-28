package com.hanghae.user.infrastructure.client;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.user.domain.dto.response.ItemInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", url = "http://localhost:8080/api/products/items")
public interface ItemClient {

    @GetMapping("/{itemId}")
    CustomResponse<ItemInfo> getItemInfo(@PathVariable("itemId") Long id);
}
