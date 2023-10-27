package com.scheduler.messaging.consumers.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.scheduler.messaging.consumers.MessageConsumer;

@Component
public class OrderMessageConsumer implements MessageConsumer<OrderEvent> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(OrderMessageConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.order.updated.name}"})
    @Override
    public void getMessage(OrderEvent message){
        LOGGER.info(String.format("Received message -> %s", message.toString()));
    }
}
