package com.hanghae.payment.exception;

import com.hanghae.common.exception.CustomException;

public class InvalidPriceException extends CustomException {

    public InvalidPriceException() {
        super(PaymentErrorCode.INVALID_PRICE_ERROR);

    }
}
