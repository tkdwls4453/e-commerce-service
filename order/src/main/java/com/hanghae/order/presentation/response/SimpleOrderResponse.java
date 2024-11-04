package com.hanghae.order.presentation.response;

import com.hanghae.order.domain.OrderStatus;
import lombok.Builder;

@Builder
public record SimpleOrderResponse(
    Long id,
    OrderStatus orderStatus,
    Long userId,
    Integer totalPrice,
    String date
) {

}
