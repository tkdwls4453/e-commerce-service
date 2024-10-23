package com.hanghae.product.presentation;

import com.hanghae.product.domain.dto.response.ProductPageDto;
import com.hanghae.product.domain.dto.response.ProductSimpleInfoDto;
import com.hanghae.product.presentation.dto.response.ProductPageResponse;
import com.hanghae.product.presentation.dto.response.ProductSimpleInfoResponse;
import java.util.List;

public class ProductDtoMapper {

    public static ProductPageResponse toProductPageResponse(ProductPageDto productPageDto) {
        List<ProductSimpleInfoResponse> productSimpleInfoResponses = productPageDto.products().stream()
            .map(ProductDtoMapper::toProductSimpleInfoResponse)
            .toList();

        return ProductPageResponse.builder()
            .totalCount(productPageDto.totalCount())
            .cursor(productPageDto.cursor())
            .products(productSimpleInfoResponses)
            .build();
    }

    private static ProductSimpleInfoResponse toProductSimpleInfoResponse(ProductSimpleInfoDto productSimpleInfoDto){
        return ProductSimpleInfoResponse.builder()
            .name(productSimpleInfoDto.name())
            .price(productSimpleInfoDto.price())
            .description(productSimpleInfoDto.description())
            .build();
    }

}
