package com.hanghae.order.exception;

import com.hanghae.common.exception.CustomException;

public class InvalidPriceException extends CustomException {

    public InvalidPriceException() {
        super(OrderErrorCode.INVALID_PRICE);

    }

}
