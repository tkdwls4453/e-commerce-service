package com.hanghae.order.infrastructure.respository;

import com.hanghae.order.application.port.OrderRepository;
import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.OrderItem;
import com.hanghae.order.domain.OrderStatus;
import com.hanghae.order.exception.NotFoundOrderException;
import com.hanghae.order.infrastructure.OrderEntity;
import com.hanghae.order.infrastructure.OrderItemEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;

    @Override
    public Order save(Order order) {

        OrderEntity orderEntity = orderJpaRepository.save(OrderEntity.from(order));
        for(OrderItem item : order.getOrderItems()) {
            OrderItemEntity orderItemEntity = orderItemJpaRepository.save(OrderItemEntity.from(item, orderEntity));
        }

        return orderEntity.toDomain();
    }

    @Override
    public List<Order> findByUserIdAndStatusIsNotPending(Integer userId) {
        List<OrderEntity> orderEntities = orderJpaRepository.findByUserIdAndOrderStatusNot(userId, OrderStatus.PAYMENT_PENDING);

        return orderEntities.stream().map(OrderEntity::toDomain).toList();
    }

    @Override
    public Order findById(Long orderId) {
        OrderEntity orderEntity = orderJpaRepository.findById(orderId).orElseThrow(
            NotFoundOrderException::new
        );

        return orderEntity.toDomain();
    }
}
