package com.hanghae.order.application.port;

import com.hanghae.order.domain.Order;
import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    List<Order> findByUserIdAndStatusIsNotPending(Integer userId);
}
