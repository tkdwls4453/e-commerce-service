package com.hanghae.product.exception;

import com.hanghae.common.exception.CustomException;
import com.hanghae.common.exception.ErrorCode;

public class NotFoundProductException extends CustomException {

    public NotFoundProductException(ErrorCode errorCode) {
        super(ProductErrorCode.NOT_FOUND_PRODUCT);
    }
}
