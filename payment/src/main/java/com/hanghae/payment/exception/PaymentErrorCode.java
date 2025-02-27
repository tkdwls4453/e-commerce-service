package com.hanghae.payment.exception;

import com.hanghae.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PaymentErrorCode implements ErrorCode {
    INVALID_PRICE_ERROR(HttpStatus.NOT_FOUND, "5001", "유효하지 않은 가격입니다."),
    NOT_FOUND_PAYMENT(HttpStatus.NOT_FOUND, "5002", "존재하지 않는 결제 정보입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
