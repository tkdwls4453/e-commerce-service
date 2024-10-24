package com.hanghae.user.exception;

import com.hanghae.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
    INVALID_EMAIL_VALUE(HttpStatus.BAD_REQUEST, "1001", "이메일 형식이 맞지앖습니다."),
    NOT_FOUND_EMAIL(HttpStatus.BAD_REQUEST, "1002", "가입되지 않은 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
