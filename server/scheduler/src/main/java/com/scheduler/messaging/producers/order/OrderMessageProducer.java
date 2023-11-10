package com.scheduler.messaging.producers.order;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Component;

import com.scheduler.messaging.producers.MessageProducer;

@Component
public class OrderMessageProducer extends MessageProducer{

    public OrderMessageProducer(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }
}
