package com.hanghae.common.exception;

import com.hanghae.common.response.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(value = Integer.MAX_VALUE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse> bindingException(MethodArgumentNotValidException e){
        log.error("[error] message: {}", e.getMessage());

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                CustomResponse.error(GlobalErrorCode.INVALID_INPUT_VALUE)
            );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse> catchRuntimeException(RuntimeException e) {
        log.error("[error] message: {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                CustomResponse.error(GlobalErrorCode.INTERNAL_SERVER_ERROR)
            );
    }
}
