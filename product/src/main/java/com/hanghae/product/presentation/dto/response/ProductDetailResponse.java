package com.hanghae.product.presentation.dto.response;

import com.hanghae.product.domain.dto.response.ItemDto;
import java.util.List;
import lombok.Builder;

@Builder
public record ProductDetailResponse(
    String name,
    String description,
    List<ItemDto> items
) {

}
