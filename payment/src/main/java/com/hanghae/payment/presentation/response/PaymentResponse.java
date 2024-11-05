package com.hanghae.payment.presentation.response;

import com.hanghae.payment.domain.PaymentStatus;
import lombok.Builder;

@Builder
public record PaymentResponse(
    Long id,
    Long orderId,
    Integer amount,
    String paymentStatus,
    String createdAt,
    String updatedAt
) {

}
