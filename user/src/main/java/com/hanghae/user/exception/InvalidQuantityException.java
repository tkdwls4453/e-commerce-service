package com.hanghae.user.exception;

import com.hanghae.common.exception.CustomException;
import com.hanghae.common.exception.ErrorCode;
import com.hanghae.common.exception.GlobalErrorCode;

public class InvalidQuantityException extends CustomException {

    public InvalidQuantityException() {
        super(GlobalErrorCode.INVALID_INPUT_VALUE);
    }
}
