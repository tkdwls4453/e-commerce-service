package com.hanghae.order.domain.dto.response;

import com.hanghae.order.application.client.response.ItemProductResponse;
import com.hanghae.order.domain.dto.request.OrderCreateDto;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public static OrderItemDto from(ItemProductResponse response, List<OrderCreateDto> orderCreateDtoList){

        Integer quantity = orderCreateDtoList.stream()
            .filter(orderCreateDto -> Objects.equals(orderCreateDto.itemId(), response.itemId()))
            .map(OrderCreateDto::quantity)
            .findFirst()
            .orElse(null);

        return OrderItemDto.builder()
            .itemId(response.itemId())
            .productName(response.productName())
            .productDescription(response.description())
            .color(response.color())
            .size(response.size())
            .price(response.price())
            .quantity(quantity)
            .build();
    }

}
