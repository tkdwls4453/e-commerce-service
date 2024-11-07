package com.hanghae.order.infrastructure;

import com.hanghae.common.infrastructure.BaseEntity;
import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.OrderItem;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="order_items")
public class OrderItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderItemQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    private Integer price;

    private Long itemId;

    @Builder
    private OrderItemEntity(Integer orderItemQuantity, OrderEntity orderEntity, Long itemId, Integer price) {
        this.orderItemQuantity = orderItemQuantity;
        this.orderEntity = orderEntity;
        this.itemId = itemId;
        this.price = price;
    }

    public static OrderItemEntity from(OrderItem orderItem, OrderEntity orderEntity) {
        return OrderItemEntity.builder()
            .itemId(orderItem.getItemId())
            .orderItemQuantity(orderItem.getOrderItemQuantity())
            .price(orderItem.getPrice())
            .orderEntity(orderEntity)
            .build();
    }

    public OrderItem toDomain(Order order){
        return OrderItem.builder()
            .id(this.id)
            .itemId(this.itemId)
            .orderItemQuantity(this.orderItemQuantity)
            .price(this.price)
            .order(order)
            .build();
    }
}
