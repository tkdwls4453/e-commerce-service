package com.hanghae.order.infrastructure.respository;

import com.hanghae.order.infrastructure.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {

}
