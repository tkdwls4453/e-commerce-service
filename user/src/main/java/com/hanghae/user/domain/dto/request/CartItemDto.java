package com.hanghae.user.domain.dto.request;

import lombok.Builder;

@Builder
public record CartItemDto(
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
