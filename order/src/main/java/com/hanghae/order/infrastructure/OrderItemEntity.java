package com.hanghae.order.infrastructure;

import com.hanghae.common.infrastructure.BaseEntity;
import com.hanghae.order.domain.OrderItem;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name="order_items")
public class OrderItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderItemQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    private Long itemId;

    @Builder
    private OrderItemEntity(Integer orderItemQuantity, OrderEntity orderEntity, Long itemId) {
        this.orderItemQuantity = orderItemQuantity;
        this.orderEntity = orderEntity;
        this.itemId = itemId;
    }

    public static OrderItemEntity from(OrderItem orderItem, OrderEntity orderEntity) {
        return OrderItemEntity.builder()
            .itemId(orderItem.getItemId())
            .orderItemQuantity(orderItem.getOrderItemQuantity())
            .orderEntity(orderEntity)
            .build();
    }

}
