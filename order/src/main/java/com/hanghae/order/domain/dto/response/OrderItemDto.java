package com.hanghae.order.domain.dto.response;

import com.hanghae.order.application.client.response.ItemProductResponse;
import java.util.Map;
import lombok.Builder;

@Builder
public record OrderItemDto(
    Long itemId,
    String productName,
    String productDescription,
    String color,
    Integer size,
    Integer price,
    Integer quantity
) {

    public static OrderItemDto from(ItemProductResponse response, Map<Long, Integer> quantityMap){
        return OrderItemDto.builder()
            .itemId(response.itemId())
            .productName(response.productName())
            .productDescription(response.description())
            .color(response.color())
            .size(response.size())
            .price(response.price())
            .quantity(quantityMap.get(response.itemId()))
            .build();
    }

}
