package com.hanghae.user.exception;

import com.hanghae.common.exception.CustomException;

public class InvalidEmailException extends CustomException {

    public InvalidEmailException() {
        super(UserErrorCode.INVALID_EMAIL_VALUE);
    }
}
