package com.hanghae.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanghae.common.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> {

    private String code;
    private String message;
    private T data;


    @Builder
    private CustomResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CustomResponse ok(){
        return CustomResponse.builder()
            .code("200")
            .message("ok")
            .build();
    }

    public static <T> CustomResponse ok(T data){
        return CustomResponse.builder()
            .code("200")
            .message("ok")
            .data(data)
            .build();
    }

    public static CustomResponse error(ErrorCode errorCode){
        return CustomResponse.builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .build();
    }

    public static CustomResponse error(String code, String message){
        return CustomResponse.builder()
            .code(code)
            .message(message)
            .build();
    }
}
