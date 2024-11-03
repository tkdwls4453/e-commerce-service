package com.hanghae.order.infrastructure;

import com.hanghae.common.infrastructure.BaseEntity;
import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="orders")
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer totalPrice;

    @Builder
    private OrderEntity(OrderStatus orderStatus, Long userId, Integer totalPrice) {
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    public static OrderEntity from(Order order) {
        return OrderEntity.builder()
            .orderStatus(order.getOrderStatus())
            .userId(order.getUserId())
            .totalPrice(order.getTotalPrice())
            .build();
    }

    public Order toDomain() {
        return Order.builder()
            .id(this.id)
            .orderStatus(this.orderStatus)
            .userId(this.userId)
            .totalPrice(this.totalPrice)
            .build();
    }
}
