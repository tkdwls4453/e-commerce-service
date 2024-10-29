package com.hanghae.product.application;

import com.hanghae.product.application.port.ProductQueryRepository;
import com.hanghae.product.domain.dto.response.ItemInfoDto;
import com.hanghae.product.domain.dto.response.ProductDetailDto;
import com.hanghae.product.domain.dto.response.ProductPageDto;

import com.hanghae.product.exception.NotFoundItemException;
import com.hanghae.product.infrastructure.ItemEntity;
import com.hanghae.product.infrastructure.repository.ItemJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductQueryRepository productQueryRepository;
    private final ItemJpaRepository itemJpaRepository;


    public ProductPageDto getAllProductInfo(Long cursor, Integer size){
        return productQueryRepository.findAllActiveByCursor(cursor, size);
    }


    public ProductDetailDto getProductDetail(Long productId){
        return productQueryRepository.findByIdWithItem(productId);
    }


    public boolean isExistsAndActive(Long productId) {
        return productQueryRepository.existsByIdAndActive(productId);
    }

    public ItemInfoDto getItemInfo(Long itemId) {
        ItemEntity itemEntity = itemJpaRepository.findById(itemId).orElseThrow(
            NotFoundItemException::new
        );

        return itemEntity.toInfoDto();
    }
}
