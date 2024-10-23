package com.hanghae.product.infrastructure.repository;

import com.hanghae.product.application.port.ProductQueryRepository;
import com.hanghae.product.domain.Product;
import com.hanghae.product.domain.dto.response.ProductPageDto;
import com.hanghae.product.domain.dto.response.ProductSimpleInfoDto;
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
}
