package com.hanghae.payment.application;

import com.hanghae.payment.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSenderService {

    private final RabbitTemplate rabbitTemplate;
    private final Integer delayInMilliseconds =  24 * 60 * 1000; // TODO: yml 파일로 빼자
    public RabbitMQSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendDelayedMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE_NAME, RabbitMQConfig.DELAY_ROUTING_KEY, message, msg -> {
            System.out.println();
            msg.getMessageProperties().setHeader("x-delay", delayInMilliseconds);
            return msg;
        });
    }
}
