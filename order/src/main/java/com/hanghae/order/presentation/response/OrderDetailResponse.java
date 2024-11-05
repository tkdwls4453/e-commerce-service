package com.hanghae.order.presentation.response;

import java.util.List;
import lombok.Builder;

@Builder
public record OrderDetailResponse(
    Long id,
    String orderStatus,
    Long userId,
    Integer totalPrice,
    List<OrderItemResponse> items
) {

}
