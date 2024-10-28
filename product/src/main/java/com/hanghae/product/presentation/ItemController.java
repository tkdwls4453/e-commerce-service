package com.hanghae.product.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.product.application.ProductService;
import com.hanghae.product.presentation.dto.response.ItemResponse;
import com.hanghae.product.presentation.dtoMapper.ItemDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products/items")
public class ItemController {

    private final ProductService productService;


    @GetMapping("/{itemId}")
    public CustomResponse<ItemResponse> getItemInfo(
        @PathVariable(value = "itemId") Long itemId
    ) {
        return CustomResponse.success(ItemDtoMapper.toItemResponse(productService.getItemInfo(itemId)));
    }
}
