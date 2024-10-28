package com.hanghae.user.application.port;

public interface CartItemRepository {

    void createAndAddCartItem(Long userId, Long productId, int quantity);
}
