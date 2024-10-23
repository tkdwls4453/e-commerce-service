package com.hanghae.product.domain.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ProductDetailDto(
    String name,
    String description,
    List<ItemDto> items
) {

}
