package com.hanghae.product.application.port;

import com.hanghae.product.domain.dto.response.ProductDetailDto;
import com.hanghae.product.domain.dto.response.ProductPageDto;

public interface ProductQueryRepository {

    ProductPageDto findAllActiveByCursor(Long cursor, int size);

    ProductDetailDto findByIdWithItem(Long productId);

    boolean existsById(Long productId);

    boolean existsByIdAndActive(Long productId);
}
