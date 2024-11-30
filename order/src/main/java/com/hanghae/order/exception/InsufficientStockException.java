package com.hanghae.order.exception;

import com.hanghae.common.exception.CustomException;

public class InsufficientStockException extends CustomException {

    public InsufficientStockException() {
        super(OrderErrorCode.INSUFFICIENT_STOCK);
    }
}
