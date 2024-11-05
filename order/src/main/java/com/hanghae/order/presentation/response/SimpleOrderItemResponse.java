package com.hanghae.order.presentation.response;

import lombok.Builder;

@Builder
public record SimpleOrderItemResponse(
    Long itemId,
    Integer price,
    Integer quantity
) {

}
