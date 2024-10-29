package com.hanghae.user.domain;

import com.hanghae.user.exception.InvalidQuantityException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartItem {

    private Long id;
    private final User user;
    private final Long ItemId;
    private final Integer quantity;

    @Builder
    private CartItem(Long id, User user, Long itemId, Integer quantity) {

        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }

        this.id = id;
        this.user = user;
        this.ItemId = itemId;
        this.quantity = quantity;
    }
}
