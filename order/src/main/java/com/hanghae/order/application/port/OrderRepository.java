package com.hanghae.order.application.port;

import com.hanghae.order.domain.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    List<Order> findByUserIdAndStatusIsNotPending(Long userId);

    Order findById(Long orderId);

    void update(Order order);
}