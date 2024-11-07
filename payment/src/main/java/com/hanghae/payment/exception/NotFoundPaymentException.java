package com.hanghae.payment.exception;


import com.hanghae.common.exception.CustomException;
import com.hanghae.common.exception.ErrorCode;

public class NotFoundPaymentException extends CustomException {

    public NotFoundPaymentException() {
        super(PaymentErrorCode.NOT_FOUND_PAYMENT);
    }
}
