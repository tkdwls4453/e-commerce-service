package com.hanghae.product.presentation.dto.response;

import lombok.Builder;

@Builder
public record ItemProductResponse(
    String productName,
    String description,
    String color,
    Integer size,
    Integer price
) {

}
