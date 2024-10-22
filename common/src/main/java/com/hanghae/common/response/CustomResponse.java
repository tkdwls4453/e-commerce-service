package com.hanghae.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanghae.common.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> {

    private final String code;
    private final String message;
    private final T data;

    @Builder
    private CustomResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CustomResponse<Void> success() {
        return createResponse("200", "ok", null);
    }

    public static <T> CustomResponse<T> success(T data) {
        return createResponse("200", "ok", data);
    }

    public static CustomResponse<Void> failure(ErrorCode errorCode) {
        return createResponse(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static CustomResponse<Void> failure(String code, String message) {
        return createResponse(code, message, null);
    }

    private static <T> CustomResponse<T> createResponse(String code, String message, T data) {
        return CustomResponse.<T>builder()
            .code(code)
            .message(message)
            .data(data)
            .build();
    }
}
