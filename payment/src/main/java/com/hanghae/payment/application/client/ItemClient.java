package com.hanghae.payment.application.client;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.payment.application.client.request.RestoreStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="product", url = "http://localhost:8080/internal/products/items")
public interface ItemClient {

    @PostMapping("/restore")
    CustomResponse<String> restoreStock(@RequestBody RestoreStockRequest request);
}
