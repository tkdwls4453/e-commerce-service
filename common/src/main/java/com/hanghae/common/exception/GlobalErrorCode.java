package com.hanghae.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode{
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5000", "서버 내부 오류"),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "5001", "적절하지 않은 요청");

    private HttpStatus httpStatus;
    private String code;
    private String message;
}
