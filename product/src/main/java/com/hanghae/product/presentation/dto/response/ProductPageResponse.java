package com.hanghae.product.presentation.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record ProductPageResponse(
    int totalCount,
    Long cursor,
    List<ProductSimpleInfoResponse> products
) {

}
