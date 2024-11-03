package com.hanghae.order.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Order {
    private final Long id;

    private OrderStatus orderStatus;

    private final Long userId;

    private Integer totalPrice;

    private final List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    private Order(Long id, OrderStatus orderStatus, Long userId, Integer totalPrice) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public static Order create(Long userId) {
        return Order.builder()
            .orderStatus(OrderStatus.PAYMENT_PENDING)
            .userId(userId)
            .build();
    }

    public void addOrderItem(List<OrderItem> orderItems) {
        this.orderItems.addAll(orderItems);
        this.totalPrice = calculateTotalPrice(orderItems);
    }

    private static Integer calculateTotalPrice(List<OrderItem> orderItems) {
        int totalPrice = 0;

        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getPrice() * orderItem.getOrderItemQuantity();
        }

        return totalPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order order)) {
            return false;
        }
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
