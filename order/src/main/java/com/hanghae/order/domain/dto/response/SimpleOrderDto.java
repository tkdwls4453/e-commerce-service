package com.hanghae.order.domain.dto.response;

import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.OrderStatus;
import lombok.Builder;

@Builder
public record SimpleOrderDto(
    Long id,
    OrderStatus orderStatus,
    Long userId,
    Integer totalPrice,
    String date
) {

    public static SimpleOrderDto from(Order order){
        return SimpleOrderDto.builder()
            .id(order.getId())
            .orderStatus(order.getOrderStatus())
            .userId(order.getUserId())
            .totalPrice(order.getTotalPrice())
            .date(order.getCreateTime().toString())
            .build();
    }
}
