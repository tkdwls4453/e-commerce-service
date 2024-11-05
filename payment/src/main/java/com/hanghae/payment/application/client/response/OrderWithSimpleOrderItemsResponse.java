package com.hanghae.payment.application.client.response;

import java.util.List;
import lombok.Builder;

@Builder
public record OrderWithSimpleOrderItemsResponse(
    Long id,
    String orderStatus,
    Long userId,
    Integer totalPrice,
    String date,
    List<SimpleOrderItemResponse> orderItems
) {


}
