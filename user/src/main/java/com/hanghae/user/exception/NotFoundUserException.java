package com.hanghae.user.exception;

import com.hanghae.common.exception.CustomException;
import com.hanghae.common.exception.ErrorCode;

public class NotFoundUserException extends CustomException {

    public NotFoundUserException() {
        super(UserErrorCode.NOT_FOUND_USER);
    }
}
