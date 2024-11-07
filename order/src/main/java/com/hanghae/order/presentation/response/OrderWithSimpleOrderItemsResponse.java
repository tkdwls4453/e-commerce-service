package com.hanghae.order.presentation.response;

import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.dto.response.SimpleOrderItemDto;
import java.util.List;
import lombok.Builder;

@Builder
public record OrderWithSimpleOrderItemsResponse(
    Long id,
    String orderStatus,
    Long userId,
    Integer totalPrice,
    String date,
    List<SimpleOrderItemResponse> orderItems
) {

    public static OrderWithSimpleOrderItemsResponse create(Order order, List<SimpleOrderItemDto> orderItemDtos) {
        return OrderWithSimpleOrderItemsResponse.builder()
            .id(order.getId())
            .orderStatus(order.getOrderStatus().toString())
            .userId(order.getUserId())
            .totalPrice(order.getTotalPrice())
            .date(order.getCreatedAt().toString())
            .build();
    }

}
