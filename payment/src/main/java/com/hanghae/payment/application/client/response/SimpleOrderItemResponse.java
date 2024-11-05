package com.hanghae.payment.application.client.response;

import lombok.Builder;

@Builder
public record SimpleOrderItemResponse(
    Long itemId,
    Integer price,
    Integer quantity
) {

}
