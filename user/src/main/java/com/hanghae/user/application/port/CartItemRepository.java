package com.hanghae.user.application.port;

import com.hanghae.user.domain.CartItem;
import com.hanghae.user.domain.dto.request.CartItemDto;
import java.util.List;

public interface CartItemRepository {

    void createAndAddCartItem(Long userId, Long productId, int quantity);


    List<CartItem> getCartItems(Long userId);
}
