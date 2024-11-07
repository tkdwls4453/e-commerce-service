package com.hanghae.payment.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentStatus {
    PAYMENT_PENDING("결제중"),
    PAYMENT_FAILED("결제실패"),
    PAYMENT_COMPLETED("결제완료"),
    PAYMENT_CANCELED("결제취소");

    private final String message;
}
