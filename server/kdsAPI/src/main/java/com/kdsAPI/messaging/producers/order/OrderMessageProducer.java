package com.kdsAPI.messaging.producers.order;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.kdsAPI.messaging.producers.MessageProducer;

@Component
public class OrderMessageProducer extends MessageProducer<OrderEvent>{

    public OrderMessageProducer(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }
}
