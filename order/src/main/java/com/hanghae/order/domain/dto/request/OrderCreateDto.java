package com.hanghae.order.domain.dto.request;

import com.hanghae.order.application.client.response.ItemProductResponse;
import lombok.Builder;

@Builder
public record OrderCreateDto(
            Long itemId,
            Integer quantity
) {

    public boolean isValidOrder(ItemProductResponse realItemInfo) {
        return quantity <= realItemInfo.stockQuantity();
    }
}
