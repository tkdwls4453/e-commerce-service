package com.hanghae.order.presentation.mapper;

import com.hanghae.order.domain.dto.response.OrderDto;
import com.hanghae.order.domain.dto.response.OrderItemDto;
import com.hanghae.order.presentation.response.OrderItemResponse;
import com.hanghae.order.presentation.response.OrderResponse;
import java.util.List;

public class OrderDtoMapper {

    public static OrderResponse toOrderResponse(OrderDto orderDto) {

        List<OrderItemResponse> itemResponseList = orderDto.items().stream()
            .map(OrderDtoMapper::toOrderItemResponse).toList();

        return OrderResponse.builder()
            .id(orderDto.id())
            .orderStatus(orderDto.orderStatus())
            .userId(orderDto.userId())
            .totalPrice(orderDto.totalPrice())
            .items(itemResponseList)
            .build();
    }

    private static OrderItemResponse toOrderItemResponse(OrderItemDto orderItemDto) {
        return OrderItemResponse.builder()
            .itemId(orderItemDto.itemId())
            .productName(orderItemDto.productName())
            .productDescription(orderItemDto.productDescription())
            .color(orderItemDto.color())
            .size(orderItemDto.size())
            .price(orderItemDto.price())
            .build();
    }
}
