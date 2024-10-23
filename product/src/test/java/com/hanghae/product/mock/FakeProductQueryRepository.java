package com.hanghae.product.mock;

import com.hanghae.product.application.port.ProductQueryRepository;
import com.hanghae.product.application.port.ProductRepository;
import com.hanghae.product.domain.Product;
import com.hanghae.product.domain.dto.response.ProductDetailDto;
import com.hanghae.product.domain.dto.response.ProductPageDto;
import com.hanghae.product.domain.dto.response.ProductSimpleInfoDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class FakeProductQueryRepository implements ProductQueryRepository {

    private final Map<Long, Product> store = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);


    public Product save(Product product) {
        long id = idCounter.getAndIncrement();
        Product updatedProduct = product.updateId(id);

        store.put(id, updatedProduct);
        return updatedProduct;
    }

    @Override
    public ProductPageDto findAllActiveByCursor(Long cursor, int size) {
        List<Product> products = store.values().stream()
            .filter(Product::isActive)
            .dropWhile(product -> cursor != null && product.getId() <= cursor)
            .limit(size)
            .toList();

        List<ProductSimpleInfoDto> productSimpleInfoDtos = products.stream()
            .map(ProductSimpleInfoDto::from)
            .toList();

        Long newCursor = products.isEmpty() ? null : products.getLast().getId();
        int totalCount = (int) store.values().stream().filter(Product::isActive).count();

        return ProductPageDto.builder()
            .totalCount(totalCount)
            .cursor(newCursor)
            .products(productSimpleInfoDtos)
            .build();
    }

    @Override
    public ProductDetailDto findByIdWithItem(Long productId) {

        return null;
    }

}
