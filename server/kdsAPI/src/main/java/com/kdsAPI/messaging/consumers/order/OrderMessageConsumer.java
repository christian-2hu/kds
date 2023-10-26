package com.kdsAPI.messaging.consumers.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.messaging.consumers.MessageConsumer;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.services.AbstractService;

import lombok.RequiredArgsConstructor;



@Component
@RequiredArgsConstructor
public class OrderMessageConsumer implements MessageConsumer<OrderDTO> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(OrderMessageConsumer.class);
    private final AbstractService<FoodOrder, OrderDTO> storeOrderService;

    @RabbitListener(queues = {"${rabbitmq.queue.order.created.name}"})
    @Override
    public void getMessage(OrderDTO message){
        LOGGER.info(String.format("Received message -> %s", message.toString()));
        FoodOrder order = storeOrderService.save(message);
        LOGGER.info(String.format("Order got from event was saved with id %s!", order.getId()));
    }
}
