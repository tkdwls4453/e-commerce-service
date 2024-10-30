package com.hanghae.order.infrastructure;

import com.hanghae.common.infrastructure.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
}
