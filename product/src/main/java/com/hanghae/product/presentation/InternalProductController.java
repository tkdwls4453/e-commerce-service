package com.hanghae.product.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("internal/products")
public class InternalProductController {
    private final ProductService productService;

    @GetMapping("/{productId}/exists")
    public CustomResponse<Boolean> isExistsProduct(
        @PathVariable(value = "productId") Long productId
    ){
        boolean result = productService.isExistsAndActive(productId);
        return CustomResponse.success(result);
    }
}
