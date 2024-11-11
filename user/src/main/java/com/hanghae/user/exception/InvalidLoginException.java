package com.hanghae.user.exception;

import com.hanghae.common.exception.CustomException;
import com.hanghae.common.exception.ErrorCode;

public class InvalidLoginException extends CustomException {

    public InvalidLoginException() {
        super(UserErrorCode.INVALID_LOGIN);
    }
}
