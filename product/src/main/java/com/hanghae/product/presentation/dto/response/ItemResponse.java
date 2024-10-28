package com.hanghae.product.presentation.dto.response;

import lombok.Builder;

@Builder
public record ItemResponse(
    Long id,
    String color,
    Integer size,
    Integer stockQuantity,
    Integer price
) {

}
