package com.hanghae.common.exception;

import com.hanghae.common.response.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(value = Integer.MIN_VALUE)
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<CustomResponse> customException(CustomException e) {
        log.info("[error] {}", e.getMessage());

        return ResponseEntity
            .status(e.getHttpStatus())
            .body(CustomResponse.error(e.getCode(), e.getMessage()));
    }

}
