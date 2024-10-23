package com.hanghae.product.domain.dto.response;

import com.hanghae.product.domain.Product;
import lombok.Builder;

@Builder
public record ProductSimpleInfoDto(
    Long id,
    String name,
    String description,
    Integer price
) {

    public static ProductSimpleInfoDto from(Product product) {
        return ProductSimpleInfoDto.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .build();
    }
}
