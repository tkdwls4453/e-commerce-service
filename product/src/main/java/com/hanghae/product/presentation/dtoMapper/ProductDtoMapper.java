package com.hanghae.product.presentation.dtoMapper;

import com.hanghae.product.domain.dto.response.ProductDetailDto;
import com.hanghae.product.domain.dto.response.ProductPageDto;
import com.hanghae.product.domain.dto.response.ProductSimpleInfoDto;
import com.hanghae.product.presentation.dto.response.ProductDetailResponse;
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
            .id(productSimpleInfoDto.id())
            .name(productSimpleInfoDto.name())
            .price(productSimpleInfoDto.price())
            .description(productSimpleInfoDto.description())
            .build();
    }

    public static ProductDetailResponse toProductDetailResponse(ProductDetailDto productDetail) {
        return ProductDetailResponse.builder()
            .name(productDetail.name())
            .description(productDetail.description())
            .items(productDetail.items())
            .build();
    }
}
