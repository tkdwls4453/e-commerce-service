package com.hanghae.order.application;

import com.hanghae.order.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RabbitMqReceiverService {

    private final OrderService orderService;

    @RabbitListener(queues = RabbitMQConfig.DELAY_QUEUE_NAME)
    public void receiveMessage(String message) {

        Long orderId = Long.parseLong(message);
        orderService.advanceOrderStatus(orderId);
    }
}
