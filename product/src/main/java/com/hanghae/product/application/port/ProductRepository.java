package com.hanghae.product.application.port;

import com.hanghae.product.domain.Product;

public interface ProductRepository {

    Product save(Product product);
}
