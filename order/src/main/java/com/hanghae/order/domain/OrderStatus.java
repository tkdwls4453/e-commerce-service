package com.hanghae.order.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {

    PAYMENT_PENDING("결제대기"),
    PREPARING("준비중"),
    SHIPPING("배송중"),
    DELIVERED("배송완료"),
    CANCELLED("주문취소");

    private final String message;
}
