package com.hanghae.order.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.order.application.OrderService;
import com.hanghae.order.domain.dto.response.OrderDto;
import com.hanghae.order.presentation.mapper.OrderDtoMapper;
import com.hanghae.order.presentation.request.OrderCreateRequest;
import com.hanghae.order.presentation.response.OrderDetailResponse;
import com.hanghae.order.presentation.response.OrderResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class ExternalOrderController {

    private final OrderService orderService;

    @PostMapping
    public CustomResponse<OrderDetailResponse> createOrder(
        @RequestBody OrderCreateRequest orderCreateRequest,
        @RequestHeader("X-User-Id") Long userId
    ){

        OrderDto orderDto = orderService.createOrder(userId, orderCreateRequest.infos());
        return CustomResponse.success(OrderDtoMapper.toOrderResponse(orderDto));
    }

    @GetMapping
    public CustomResponse<List<OrderResponse>> readOrders(
        @RequestHeader("X-User-Id") Long userId
    ){
        List<OrderResponse> body = orderService.getOrders(userId).stream().map(OrderDtoMapper::toSimpleOrderResponse).toList();

        return CustomResponse.success(body);
    }
}
