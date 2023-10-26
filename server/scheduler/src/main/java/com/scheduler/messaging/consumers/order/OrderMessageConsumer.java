package com.scheduler.messaging.consumers.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.scheduler.messaging.consumers.MessageConsumer;
import com.scheduler.models.ifood.Order;


@Component
public class OrderMessageConsumer implements MessageConsumer<Order> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(OrderMessageConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    @Override
    public void getMessage(Order message){
        LOGGER.info(String.format("Received message -> %s", message.toString()));
    }
}
