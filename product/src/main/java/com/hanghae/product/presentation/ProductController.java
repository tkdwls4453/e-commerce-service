package com.hanghae.product.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.product.application.ProductService;
import com.hanghae.product.domain.dto.response.ProductDetailDto;
import com.hanghae.product.domain.dto.response.ProductPageDto;
import com.hanghae.product.presentation.dto.response.ProductDetailResponse;
import com.hanghae.product.presentation.dto.response.ProductPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public CustomResponse<ProductPageResponse> getProducts(
        @RequestParam(value = "cursor" , required = false) Long cursor,
        @RequestParam(value = "size" , required = false, defaultValue = "10") Integer size
    ){
        ProductPageDto productPageDto = productService.getAllProductInfo(cursor, size);

        ProductPageResponse productPageResponse = ProductDtoMapper.toProductPageResponse(productPageDto);
        return CustomResponse.success(productPageResponse);
    }

    @GetMapping("/{productId}")
    public CustomResponse<ProductDetailResponse> getProductDetail(
        @PathVariable(value = "productId") Long productId
    ){
        ProductDetailDto productDetail = productService.getProductDetail(productId);
        return CustomResponse.success(ProductDtoMapper.toProductDetailResponse(productDetail));
    }

    @GetMapping("/{productId}/exists")
    public CustomResponse<Boolean> isExistsProduct(
        @PathVariable(value = "productId") Long productId
    ){
        boolean result = productService.isExistsAndActive(productId);
        return CustomResponse.success(result);
    }
}
