package com.hanghae.order.application.port;

import com.hanghae.order.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
