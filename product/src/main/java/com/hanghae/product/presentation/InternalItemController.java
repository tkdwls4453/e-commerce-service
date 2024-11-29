package com.hanghae.product.presentation;

import com.hanghae.common.response.CustomResponse;
import com.hanghae.product.application.ItemService;
import com.hanghae.product.application.ProductService;
import com.hanghae.product.domain.dto.response.ItemProductDto;
import com.hanghae.product.presentation.dto.request.ReduceStockRequest;
import com.hanghae.product.presentation.dto.response.ItemProductResponse;
import com.hanghae.product.presentation.mapper.ItemDtoMapper;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/products/items")
public class InternalItemController {
    private final ItemService itemService;

    @GetMapping
    public CustomResponse<List<ItemProductResponse>> getItemProducts(
        @RequestParam("itemIds") List<Long> itemIds
    ) {
        List<ItemProductDto> itemProductDtos = itemService.getItemProducts(itemIds);

        List<ItemProductResponse> result = itemProductDtos.stream().map(
                ItemDtoMapper::toItemProductResponse
            )
            .toList();

        return CustomResponse.success(result);
    }

//    @PostMapping("/deduct")
//    public CustomResponse<String> reduceStock(
//        @RequestBody ReduceStockRequest request
//    ) throws IOException {
//        itemService.reduceStock(request.infos());
//        return CustomResponse.success(null);
//    }

//    @PostMapping("/deduct")
//    public CustomResponse<List<ItemProductResponse>> reduceStockAndGetInfo(
//        @RequestBody ReduceStockRequest request
//    ) throws IOException {
//
//        List<ItemProductDto> itemProductDtos = itemService.reduceStock(request.infos());
//
//        List<ItemProductResponse> result = itemProductDtos.stream().map(
//                ItemDtoMapper::toItemProductResponse
//            )
//            .toList();
//
//        return CustomResponse.success(result);
//    }

    @PostMapping("/restore")
    public CustomResponse<String> restoreStock(
        @RequestBody ReduceStockRequest request
    ){
        itemService.restoreStock(request.infos());
        return CustomResponse.success(null);
    }
}
