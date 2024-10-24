package com.hanghae.user.exception;

import com.hanghae.common.exception.CustomException;

public class NotFoundEmailException extends CustomException {

    public NotFoundEmailException() {
        super(UserErrorCode.NOT_FOUND_EMAIL);
    }
}
