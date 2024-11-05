package com.hanghae.payment.presentation.mapper;

import com.hanghae.payment.domain.dto.response.PaymentDto;
import com.hanghae.payment.presentation.response.PaymentResponse;

public class PaymentDtoMapper {

    public static PaymentResponse toPaymentResponse(PaymentDto paymentDto) {
        return PaymentResponse.builder()
            .id(paymentDto.id())
            .orderId(paymentDto.orderId())
            .amount(paymentDto.amount())
            .paymentStatus(paymentDto.paymentStatus())
            .createdAt(paymentDto.createdAt())
            .updatedAt(paymentDto.updatedAt())
            .build();
    }
}
