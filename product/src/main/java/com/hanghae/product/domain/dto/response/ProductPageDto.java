package com.hanghae.product.domain.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ProductPageDto(
    int totalCount,
    Long cursor,
    List<ProductSimpleInfoDto> products
) {

}
