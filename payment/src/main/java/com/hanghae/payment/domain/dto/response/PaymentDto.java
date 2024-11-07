package com.hanghae.payment.domain.dto.response;

import com.hanghae.payment.domain.Payment;
import lombok.Builder;

@Builder
public record PaymentDto(
    Long id,
    Long orderId,
    Integer amount,
    String paymentStatus,
    String createdAt,
    String updatedAt
) {

    public static PaymentDto from(Payment payment) {
        return PaymentDto.builder()
            .id(payment.getId())
            .orderId(payment.getOrderId())
            .amount(payment.getAmount())
            .paymentStatus(payment.getPaymentStatus().toString())
            .createdAt(payment.getCreatedAt().toString())
            .updatedAt(payment.getUpdatedAt().toString())
            .build();
    }
}
