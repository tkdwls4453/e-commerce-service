package com.hanghae.order.domain.dto.response;

import com.hanghae.order.domain.Order;
import com.hanghae.order.presentation.response.OrderWithSimpleOrderItemsResponse;
import com.hanghae.order.presentation.response.SimpleOrderItemResponse;
import java.util.List;
import lombok.Builder;

@Builder
public record OrderWithSimpleOrderItemsDto(
    Long id,
    String orderStatus,
    Long userId,
    Integer totalPrice,
    String date,
    List<SimpleOrderItemDto> orderItems
) {

    public static OrderWithSimpleOrderItemsDto create(Order order, List<SimpleOrderItemDto> orderItemDtos) {
        return OrderWithSimpleOrderItemsDto.builder()
            .id(order.getId())
            .orderStatus(order.getOrderStatus().toString())
            .userId(order.getUserId())
            .totalPrice(order.getTotalPrice())
            .date(order.getCreatedAt().toString())
            .orderItems(orderItemDtos)
            .build();
    }

}
