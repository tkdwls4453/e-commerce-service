package com.hanghae.product.exception;

import com.hanghae.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductErrorCode implements ErrorCode {
    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "2001", "존재하지 않는 상품입니다."),
    NOT_FOUND_ITEM(HttpStatus.NOT_FOUND, "2003", "존재하지 않는 아이템입니다."),
    INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "2004", "재고 이상의 차감은 불가능합니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
