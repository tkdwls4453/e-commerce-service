package com.hanghae.product.application;

import static org.assertj.core.api.Assertions.*;
import com.hanghae.product.domain.Product;
import com.hanghae.product.domain.dto.response.ProductPageDto;
import com.hanghae.product.domain.dto.response.ProductSimpleInfoDto;
import com.hanghae.product.mock.FakeProductQueryRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductServiceTest {
    private ProductService productService;
    private FakeProductQueryRepository fakeProductQueryRepository;

    @BeforeEach
    void setUp() {
        fakeProductQueryRepository = new FakeProductQueryRepository();
        productService = new ProductService(fakeProductQueryRepository);
        populateRepositoryWithSampleProducts();
    }

    private void populateRepositoryWithSampleProducts() {
        for (int i = 0; i < 100; i++) {
            Product product = Product.builder()
                .name("product" + (i + 1))
                .price(i + 1000)
                .description("description")
                .active(true)
                .build();
            fakeProductQueryRepository.save(product);
        }
    }

    @Test
    @DisplayName("cursor와 size 가 주어지면 알맞은 형태의 상품 정보를 조회한다.")
    void givenCursorAndSize_whenFindAllProduct_thenReturnListProductInfo() {
        // Given
        Long cursor = 10L;
        int size = 10;
        // When
        ProductPageDto result = productService.getAllProductInfo(cursor, size);
        // Then
        List<ProductSimpleInfoDto> products = result.products();
        assertThat(products.size()).isEqualTo(size);
        assertThat(result.totalCount()).isEqualTo(100);
        assertThat(result.cursor()).isEqualTo(20L);
    }
}