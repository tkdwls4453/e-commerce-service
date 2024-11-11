package com.hanghae.order.exception;

import com.hanghae.common.exception.CustomException;
import com.hanghae.common.exception.ErrorCode;

public class InvalidOrderRequest extends CustomException {

    public InvalidOrderRequest() {
        super(OrderErrorCode.INVALID_ORDER_REQUEST);
    }
}
