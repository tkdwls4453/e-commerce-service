package com.hanghae.order.presentation.mapper;

import com.hanghae.order.domain.dto.response.OrderDto;
import com.hanghae.order.domain.dto.response.OrderItemDto;
import com.hanghae.order.domain.dto.response.OrderWithSimpleOrderItemsDto;
import com.hanghae.order.domain.dto.response.SimpleOrderDto;
import com.hanghae.order.domain.dto.response.SimpleOrderItemDto;
import com.hanghae.order.presentation.response.OrderItemResponse;
import com.hanghae.order.presentation.response.OrderDetailResponse;
import com.hanghae.order.presentation.response.OrderResponse;
import com.hanghae.order.presentation.response.OrderWithSimpleOrderItemsResponse;
import com.hanghae.order.presentation.response.SimpleOrderItemResponse;
import java.util.List;

public class OrderDtoMapper {

    public static OrderDetailResponse toOrderResponse(OrderDto orderDto) {

        List<OrderItemResponse> itemResponseList = orderDto.items().stream()
            .map(OrderDtoMapper::toOrderItemResponse).toList();

        return OrderDetailResponse.builder()
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
            .quantity(orderItemDto.quantity())
            .build();
    }

    public static OrderResponse toSimpleOrderResponse(SimpleOrderDto simpleOrderDto) {
        return OrderResponse.builder()
            .id(simpleOrderDto.id())
            .orderStatus(simpleOrderDto.orderStatus())
            .userId(simpleOrderDto.userId())
            .totalPrice(simpleOrderDto.totalPrice())
            .date(simpleOrderDto.date())
            .build();
    }

    public static OrderWithSimpleOrderItemsResponse toOrderWithSimpleOrderItemsResponse(OrderWithSimpleOrderItemsDto orderWithSimpleOrderItemsDto) {

        List<SimpleOrderItemResponse> items = orderWithSimpleOrderItemsDto.orderItems().stream()
            .map(OrderDtoMapper::toSimpleOrderItemResponse).toList();
        return OrderWithSimpleOrderItemsResponse.builder()
            .id(orderWithSimpleOrderItemsDto.id())
            .orderStatus(orderWithSimpleOrderItemsDto.orderStatus())
            .userId(orderWithSimpleOrderItemsDto.userId())
            .totalPrice(orderWithSimpleOrderItemsDto.totalPrice())
            .date(orderWithSimpleOrderItemsDto.date())
            .orderItems(items)
            .build();

    }

    private static SimpleOrderItemResponse toSimpleOrderItemResponse(SimpleOrderItemDto simpleOrderItemDto) {
        return SimpleOrderItemResponse.builder()
            .itemId(simpleOrderItemDto.itemId())
            .price(simpleOrderItemDto.price())
            .quantity(simpleOrderItemDto.quantity())
            .build();
    }


}
