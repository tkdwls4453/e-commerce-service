package com.hanghae.user.presentation.dto.response;

import lombok.Builder;

@Builder
public record CartItemResponse(
    Long id,
    Long itemId,
    String productName,
    String description,
    String color,
    Integer size,
    Integer price,
    Integer quantity
) {

}
