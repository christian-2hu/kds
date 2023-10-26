package com.kdsAPI.messaging.consumers.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.messaging.consumers.MessageConsumer;



@Component
public class OrderMessageConsumer implements MessageConsumer<OrderDTO> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(OrderMessageConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.order.created.name}"})
    @Override
    public void getMessage(OrderDTO message){
        LOGGER.info(String.format("Received message -> %s", message.toString()));
    }
}
