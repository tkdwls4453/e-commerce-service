package com.hanghae.product.domain.dto.response;

import lombok.Builder;

@Builder
public record ItemProductDto(
    String productName,
    String description,
    String color,
    Integer size,
    Integer price
) {

}
