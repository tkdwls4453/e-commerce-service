package com.hanghae.product.presentation.dtoMapper;

import com.hanghae.product.domain.dto.response.ItemInfoDto;
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
}
