package com.hanghae.order.infrastructure.respository;

import com.hanghae.order.infrastructure.OrderEntity;
import com.hanghae.order.infrastructure.OrderItemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {

    List<OrderItemEntity> findAllByOrderEntity(OrderEntity orderEntity);

}
