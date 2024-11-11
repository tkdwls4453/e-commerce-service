package com.hanghae.order.infrastructure.respository;

import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.OrderStatus;
import com.hanghae.order.infrastructure.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Query(
          """
          select o
          from OrderEntity o
          where o.userId = :userId AND o.orderStatus <> :status
          order by o.createdAt desc
          """
    )
    List<OrderEntity> findByUserIdAndOrderStatusNot(@Param("userId") Long userId, @Param("status") OrderStatus status);
}
