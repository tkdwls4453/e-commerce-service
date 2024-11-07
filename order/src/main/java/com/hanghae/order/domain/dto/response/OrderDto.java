package com.hanghae.order.domain.dto.response;

import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.OrderItem;
import com.hanghae.order.domain.OrderStatus;
import java.util.List;
import lombok.Builder;

@Builder
public record OrderDto (
            Long id,
            String orderStatus,
            Long userId,
            Integer totalPrice,
            List<OrderItemDto> items
){

    public static OrderDto from(Order order, List<OrderItemDto> items) {
        return OrderDto.builder()
            .id(order.getId())
            .orderStatus(order.getOrderStatus().toString())
            .userId(order.getUserId())
            .totalPrice(order.getTotalPrice())
            .items(items)
            .build();
    }
}
