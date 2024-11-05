package com.hanghae.order.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.order.application.OrderService;
import com.hanghae.order.domain.dto.response.OrderDto;
import com.hanghae.order.domain.dto.response.SimpleOrderDto;
import com.hanghae.order.presentation.mapper.OrderDtoMapper;
import com.hanghae.order.presentation.request.OrderCreateRequest;
import com.hanghae.order.presentation.response.OrderResponse;
import com.hanghae.order.presentation.response.SimpleOrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public CustomResponse<OrderResponse> createOrder(
        @RequestBody OrderCreateRequest orderCreateRequest
    ){
        OrderDto orderDto = orderService.createOrder(orderCreateRequest.userId(),
            orderCreateRequest.infos());
        return CustomResponse.success(OrderDtoMapper.toOrderResponse(orderDto));
    }

    @GetMapping
    public CustomResponse<List<SimpleOrderResponse>> readOrders(
        @RequestParam Integer userId
    ){
        List<SimpleOrderResponse> body = orderService.getOrders(userId).stream().map(OrderDtoMapper::toSimpleOrderResponse).toList();

        return CustomResponse.success(body);
    }

    @GetMapping("/{orderId}")
    public CustomResponse<SimpleOrderResponse> readOrder(
        @PathVariable Long orderId
    ){
        SimpleOrderDto simpleOrderDto = orderService.getOrder(orderId);
        return CustomResponse.success(OrderDtoMapper.toSimpleOrderResponse(simpleOrderDto));
    }

}
