package com.hanghae.product.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Item {

    private Long id;

    private String color;
    private Integer size;
    private Integer stockQuantity;
    private Integer price;
    private Product product;

    @Builder
    private Item(Long id, String color, Integer size, Integer stockQuantity, Integer price,
        Product product) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.product = product;
    }
}
