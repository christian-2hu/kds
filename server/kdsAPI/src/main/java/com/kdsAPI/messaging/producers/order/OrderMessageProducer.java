package com.kdsAPI.messaging.producers.order;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.kdsAPI.messaging.producers.MessageProducer;
import com.kdsAPI.models.order.Order;

@Component
public class OrderMessageProducer extends MessageProducer<Order>{

    public OrderMessageProducer(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }
}
