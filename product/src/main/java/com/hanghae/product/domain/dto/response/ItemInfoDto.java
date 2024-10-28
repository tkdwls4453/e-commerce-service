package com.hanghae.product.domain.dto.response;

import lombok.Builder;

@Builder
public record ItemInfoDto(
    Long id,
    String color,
    Integer size,
    Integer stockQuantity,
    Integer price
) {


}
