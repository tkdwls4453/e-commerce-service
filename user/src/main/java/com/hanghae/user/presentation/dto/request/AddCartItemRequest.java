package com.hanghae.user.presentation.dto.request;

import lombok.Builder;

@Builder
public record AddCartItemRequest(
    Long itemId,
    int quantity
) {

}
