package com.hanghae.product.presentation.dto.response;

import lombok.Builder;

@Builder
public record ProductSimpleInfoResponse(
    String name,
    String description,
    Integer price
) {

}
