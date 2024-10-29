package com.hanghae.user.presentation.mapper;

import com.hanghae.user.domain.dto.request.CartItemDto;
import com.hanghae.user.presentation.dto.response.CartItemResponse;

public class CartItemDtoMapper {

    public static CartItemResponse toCartItemResponse(CartItemDto cartItemDto) {
        return CartItemResponse.builder()
            .id(cartItemDto.id())
            .itemId(cartItemDto.itemId())
            .productName(cartItemDto.productName())
            .description(cartItemDto.description())
            .color(cartItemDto.color())
            .size(cartItemDto.size())
            .price(cartItemDto.price())
            .quantity(cartItemDto.quantity())
            .build();
    }
}
