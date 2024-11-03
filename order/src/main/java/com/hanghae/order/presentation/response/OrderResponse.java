package com.hanghae.order.presentation.response;

import com.hanghae.order.domain.OrderStatus;
import java.util.List;
import lombok.Builder;

@Builder
public record OrderResponse(
    Long id,
    OrderStatus orderStatus,
    Long userId,
    Integer totalPrice,
    List<OrderItemResponse> items
) {

}
