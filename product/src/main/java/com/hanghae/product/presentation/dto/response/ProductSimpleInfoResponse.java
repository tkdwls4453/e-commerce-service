package com.hanghae.product.presentation.dto.response;

import lombok.Builder;

@Builder
public record ProductSimpleInfoResponse(
    Long id,
    String name,
    String description,
    Integer price
) {

}
