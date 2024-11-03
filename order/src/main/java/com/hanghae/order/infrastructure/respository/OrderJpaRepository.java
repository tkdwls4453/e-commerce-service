package com.hanghae.order.infrastructure.respository;

import com.hanghae.order.infrastructure.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

}
