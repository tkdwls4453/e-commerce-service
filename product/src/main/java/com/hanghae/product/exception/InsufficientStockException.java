package com.hanghae.product.exception;

import com.hanghae.common.exception.CustomException;

public class InsufficientStockException extends CustomException {

    public InsufficientStockException() {
        super(ProductErrorCode.INSUFFICIENT_STOCK);
    }
}
