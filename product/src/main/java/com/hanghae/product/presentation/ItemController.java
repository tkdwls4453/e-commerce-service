package com.hanghae.product.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.product.application.ItemService;
import com.hanghae.product.application.ProductService;
import com.hanghae.product.domain.dto.response.ItemProductDto;
import com.hanghae.product.presentation.dto.request.GetItemProductRequest;
import com.hanghae.product.presentation.dto.response.ItemProductResponse;
import com.hanghae.product.presentation.dto.response.ItemResponse;
import com.hanghae.product.presentation.dtoMapper.ItemDtoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products/items")
public class ItemController {

    private final ProductService productService;
    private final ItemService itemService;

    @GetMapping("/{itemId}")
    public CustomResponse<ItemResponse> getItemInfo(
        @PathVariable(value = "itemId") Long itemId
    ) {
        return CustomResponse.success(ItemDtoMapper.toItemResponse(productService.getItemInfo(itemId)));
    }

    @GetMapping
    public CustomResponse<List<ItemProductResponse>> getItemProducts(
        @RequestBody GetItemProductRequest request
    ) {
        List<ItemProductDto> itemProductDtos = itemService.getItemProducts(request.itemIds());

        List<ItemProductResponse> result = itemProductDtos.stream().map(
                ItemDtoMapper::toItemProductResponse
            )
            .toList();

        return CustomResponse.success(result);
    }
}
