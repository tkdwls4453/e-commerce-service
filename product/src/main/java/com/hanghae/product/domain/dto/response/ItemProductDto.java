package com.hanghae.product.domain.dto.response;

import lombok.Builder;

@Builder
public record ItemProductDto(
    Long itemId,
    String productName,
    String description,
    String color,
    Integer size,
    Integer price,
    Integer stockQuantity
) {

}
