package com.hanghae.product.exception;

import com.hanghae.common.exception.CustomException;
import com.hanghae.common.exception.ErrorCode;

public class NotFoundItemException extends CustomException {

    public NotFoundItemException() {
        super(ProductErrorCode.NOT_FOUND_ITEM);

    }
}
