package com.hanghae.product.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.product.application.ItemService;
import com.hanghae.product.application.ProductService;
import com.hanghae.product.domain.dto.response.ItemProductDto;
import com.hanghae.product.presentation.dto.request.ReduceStockRequest;
import com.hanghae.product.presentation.dto.response.ItemProductResponse;
import com.hanghae.product.presentation.dto.response.ItemResponse;
import com.hanghae.product.presentation.mapper.ItemDtoMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products/items")
public class ExternalItemController {

    private final ProductService productService;

    @GetMapping("/{itemId}")
    public CustomResponse<ItemResponse> getItemInfo(
        @PathVariable(value = "itemId") Long itemId
    ) {
        return CustomResponse.success(ItemDtoMapper.toItemResponse(productService.getItemInfo(itemId)));
    }
}
