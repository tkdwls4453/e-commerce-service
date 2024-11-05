package com.hanghae.order.infrastructure.respository;

import com.hanghae.order.application.port.OrderItemRepository;
import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.OrderItem;
import com.hanghae.order.exception.NotFoundOrderException;
import com.hanghae.order.infrastructure.OrderEntity;
import com.hanghae.order.infrastructure.OrderItemEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final OrderItemJpaRepository orderItemJpaRepository;
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public List<OrderItem> findAllByOrder(Order order) {
        OrderEntity orderEntity = orderJpaRepository.findById(order.getId()).orElseThrow(
            NotFoundOrderException::new
        );

        return orderItemJpaRepository.findAllByOrderEntity(orderEntity)
            .stream()
            .map(orderItemEntity ->  orderItemEntity.toDomain(order)).toList();
    }
}
