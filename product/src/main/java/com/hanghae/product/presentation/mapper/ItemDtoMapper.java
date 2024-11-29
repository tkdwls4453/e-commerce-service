package com.hanghae.product.presentation.mapper;

import com.hanghae.product.domain.dto.response.ItemInfoDto;
import com.hanghae.product.domain.dto.response.ItemProductDto;
import com.hanghae.product.presentation.dto.response.ItemProductResponse;
import com.hanghae.product.presentation.dto.response.ItemResponse;


public class ItemDtoMapper {

    public static ItemResponse toItemResponse(ItemInfoDto itemInfo) {
        return ItemResponse.builder()
            .id(itemInfo.id())
            .color(itemInfo.color())
            .size(itemInfo.size())
            .stockQuantity(itemInfo.stockQuantity())
            .price(itemInfo.price())
            .build();
    }

    public static ItemProductResponse toItemProductResponse(ItemProductDto dto) {
        return ItemProductResponse.builder()
            .itemId(dto.itemId())
            .productName(dto.productName())
            .description(dto.description())
            .color(dto.color())
            .size(dto.size())
            .price(dto.price())
            .build();
    }
}
