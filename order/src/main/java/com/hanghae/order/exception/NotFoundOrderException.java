package com.hanghae.order.exception;

import com.hanghae.common.exception.CustomException;

public class NotFoundOrderException extends CustomException {

    public NotFoundOrderException() {
        super(OrderErrorCode.NOT_FOUND_ORDER);

    }

}
