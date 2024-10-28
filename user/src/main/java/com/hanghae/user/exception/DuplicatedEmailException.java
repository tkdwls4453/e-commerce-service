package com.hanghae.user.exception;

import com.hanghae.common.exception.CustomException;

public class DuplicatedEmailException extends CustomException {

    public DuplicatedEmailException() {
        super(UserErrorCode.DUPLICATED_EMAIL);
    }
}