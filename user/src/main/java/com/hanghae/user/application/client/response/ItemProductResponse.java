package com.hanghae.user.application.client.response;

import lombok.Builder;

@Builder
public record ItemProductResponse(
    Long itemId,
    String productName,
    String description,
    String color,
    Integer size,
    Integer price,
    Integer stockQuantity
) {

}
