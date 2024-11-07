package com.hanghae.order.domain.dto.response;

import com.hanghae.order.domain.OrderItem;
import lombok.Builder;
import lombok.Getter;

@Builder
public record SimpleOrderItemDto(
    Long itemId,
    Integer price,
    Integer quantity
) {

    public static SimpleOrderItemDto from(OrderItem orderItem) {
        return SimpleOrderItemDto.builder()
            .itemId(orderItem.getItemId())
            .price(orderItem.getPrice())
            .quantity(orderItem.getOrderItemQuantity())
            .build();
    }

}
