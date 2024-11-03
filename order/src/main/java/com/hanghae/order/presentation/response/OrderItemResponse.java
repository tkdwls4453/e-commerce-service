package com.hanghae.order.presentation.response;

import lombok.Builder;

@Builder
public record OrderItemResponse(
    Long itemId,
    String productName,
    String productDescription,
    String color,
    Integer size,
    Integer price
) {

}
