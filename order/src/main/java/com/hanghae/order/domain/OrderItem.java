package com.hanghae.order.domain;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderItem {
    private final Long id;
    private final Integer orderItemQuantity;
    private final Integer price;
    private final Order order;
    private final Long itemId;

    @Builder
    private OrderItem(Long id, Integer orderItemQuantity, Integer price, Order order, Long itemId) {
        this.id = id;
        this.orderItemQuantity = orderItemQuantity;
        this.price = price;
        this.order = order;
        this.itemId = itemId;
    }

    public static OrderItem create(Integer orderItemQuantity, Integer price, Long itemId) {
        return OrderItem.builder()
            .orderItemQuantity(orderItemQuantity)
            .price(price)
            .itemId(itemId)
            .build();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItem orderItem)) {
            return false;
        }
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
