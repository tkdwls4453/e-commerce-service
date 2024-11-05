package com.hanghae.payment.application.client;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.payment.application.client.response.OrderWithSimpleOrderItemsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="order", url = "http://localhost:8080/api/orders")
public interface OrderClient {

    @GetMapping("/{orderId}")
    CustomResponse<OrderWithSimpleOrderItemsResponse> readOrder(@PathVariable("orderId") Long orderId);
}
