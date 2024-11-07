package com.hanghae.order.application.port;

import com.hanghae.order.domain.Order;
import com.hanghae.order.domain.OrderItem;
import java.util.List;

public interface OrderItemRepository {

    List<OrderItem> findAllByOrder(Order order);
}
