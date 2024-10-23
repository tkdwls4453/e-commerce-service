package com.hanghae.product.infrastructure.repository;

import com.hanghae.product.application.ProductService;
import com.hanghae.product.application.port.ProductQueryRepository;
import com.hanghae.product.domain.Product;
import com.hanghae.product.domain.dto.response.ItemDto;
import com.hanghae.product.domain.dto.response.ProductDetailDto;
import com.hanghae.product.domain.dto.response.ProductPageDto;
import com.hanghae.product.domain.dto.response.ProductSimpleInfoDto;
import com.hanghae.product.exception.NotFoundProductException;
import com.hanghae.product.exception.ProductErrorCode;
import com.hanghae.product.infrastructure.ItemEntity;
import com.hanghae.product.infrastructure.ProductEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final ProductJpaQueryRepository productJpaQueryRepository;
    private final ItemJpaRepository itemJpaRepository;

    @Override
    public ProductPageDto findAllActiveByCursor(Long cursor, int size) {
        Pageable pageable = PageRequest.of(0, size);
        List<Product> products = productJpaQueryRepository.findAllByCursor(cursor, pageable)
            .stream().map(
                ProductEntity::toDomain).toList();

        List<ProductSimpleInfoDto> productSimpleInfoDtos = products.stream().map(ProductSimpleInfoDto::from)
            .toList();

        int count = productJpaQueryRepository.countIsActive();
        Long lastId = !products.isEmpty() ? products.getLast().getId() : null;

        return ProductPageDto.builder()
            .totalCount(count)
            .cursor(lastId)
            .products(productSimpleInfoDtos)
            .build();
    }

    // TODO : 여기서 쿼리가 2번 나가는데 이정도는 상관없을까?
    @Override
    public ProductDetailDto findByIdWithItem(Long productId) {
        ProductEntity productEntity = productJpaQueryRepository.findById(productId).orElseThrow(
            () -> new NotFoundProductException(ProductErrorCode.NOT_FOUND_PRODUCT)
        );

        List<ItemDto> items = itemJpaRepository.findAllByProduct(productEntity)
            .stream().map(ItemEntity::toDto).toList();

        return ProductDetailDto.builder()
            .name(productEntity.getName())
            .description(productEntity.getDescription())
            .items(items)

            .build();
    }
}
