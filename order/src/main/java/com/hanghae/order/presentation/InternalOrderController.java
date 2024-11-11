package com.hanghae.order.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.order.application.OrderService;
import com.hanghae.order.presentation.mapper.OrderDtoMapper;
import com.hanghae.order.presentation.response.OrderWithSimpleOrderItemsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("internal/orders")
public class InternalOrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public CustomResponse<OrderWithSimpleOrderItemsResponse> readOrder(
        @PathVariable Long orderId
    ){
        OrderWithSimpleOrderItemsResponse body = OrderDtoMapper.toOrderWithSimpleOrderItemsResponse(orderService.getOrder(orderId));

        return CustomResponse.success(body);
    }

    @PutMapping("/{orderId}/advance-status")
    public CustomResponse<String> advanceOrderStatus(
        @PathVariable(name = "orderId") Long orderId
    ){
        orderService.advanceOrderStatus(orderId);

        return CustomResponse.success(null);
    }
}
