package com.hanghae.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {
    SOLD_OUT("품절"),
    LESS_THAN_TEN("10개 미만"),
    MORE_THAN_TEN("10개 이상");

    private final String description;
}
