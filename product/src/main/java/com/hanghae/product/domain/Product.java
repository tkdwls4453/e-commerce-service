package com.hanghae.product.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {

    private final Long id;
    private final String name;
    private final String description;
    private final Integer price;
    private final boolean active;

    @Builder
    private Product(Long id, String name, String description, Integer price, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
    }

    public Product updateId(long id) {
        return Product.builder()
            .id(id)
            .name(this.name)
            .description(this.description)
            .price(this.price)
            .active(this.active)
            .build();
    }
}
