package com.hanghae.product.domain.dto.response;

import lombok.Builder;

@Builder
public record ItemDto(
    Long id,
    Integer price,
    String color,
    Integer size,
    String status
) {

}
