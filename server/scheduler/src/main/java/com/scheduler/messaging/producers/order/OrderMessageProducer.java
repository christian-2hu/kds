package com.scheduler.messaging.producers.order;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Component;

import com.scheduler.messaging.producers.MessageProducer;
import com.scheduler.models.ifood.Order;

@Component
public class OrderMessageProducer extends MessageProducer<Order>{

    public OrderMessageProducer(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }
}
