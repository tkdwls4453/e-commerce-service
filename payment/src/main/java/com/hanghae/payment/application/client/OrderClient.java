package com.hanghae.payment.application.client;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.payment.application.client.response.OrderWithSimpleOrderItemsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="order", url = "http://localhost:8080/api/orders")
public interface OrderClient {

    @GetMapping("/{orderId}")
    CustomResponse<OrderWithSimpleOrderItemsResponse> readOrder(@PathVariable("orderId") Long orderId);

    @PutMapping("/{orderId}/advance-status")
    CustomResponse<String> advanceOrderStatus(@PathVariable("orderId") Long orderId);

}
