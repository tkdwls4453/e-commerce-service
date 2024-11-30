package com.hanghae.order.exception;

import com.hanghae.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OrderErrorCode implements ErrorCode {
    INVALID_PRICE(HttpStatus.BAD_REQUEST, "3001", "유효하지 않은 가격입니다."),
    NOT_FOUND_ORDER(HttpStatus.BAD_REQUEST, "3003", "존재하지 않는 주문입니다."),
    INVALID_ORDER_REQUEST(HttpStatus.BAD_REQUEST, "3004", "재고가 부족합니다."),
    INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "2004", "재고 이상의 차감은 불가능합니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
