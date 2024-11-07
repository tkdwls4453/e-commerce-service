package com.hanghae.order.exception;

import com.hanghae.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OrderErrorCode implements ErrorCode {
    INVALID_PRICE(HttpStatus.BAD_REQUEST, "3001", "유효하지 않은 가격입니다."),
    NOT_FOUND_ORDER(HttpStatus.BAD_REQUEST, "3003", "존재하지 않는 주문입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
