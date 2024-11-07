package com.hanghae.product.application.port;

public interface ItemCommandRepository {

    void reduceStock(Long itemId, Integer quantity);

    void increaseStock(Long itemId, Integer quantity);
}
