package com.hanghae.order.domain.dto.request;

import lombok.Builder;

@Builder
public record OrderCreateDto(
            Long itemId,
            Integer quantity
) {
}
