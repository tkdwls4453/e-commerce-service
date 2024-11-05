package com.hanghae.order.presentation.response;

import com.hanghae.order.domain.OrderStatus;
import lombok.Builder;

@Builder
public record SimpleOrderResponse(
    Long id,
    String orderStatus,
    Long userId,
    Integer totalPrice,
    String date
) {

}
