package com.hanghae.order.presentation.response;

import lombok.Builder;

@Builder
public record OrderResponse(
    Long id,
    String orderStatus,
    Long userId,
    Integer totalPrice,
    String date
) {

}
