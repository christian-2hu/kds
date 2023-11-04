package com.scheduler.messaging.consumers.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.scheduler.messaging.consumers.MessageConsumer;
import com.scheduler.models.ifood.IfoodRequestCancellation;
import com.scheduler.services.DeliveryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderCanceledMessageConsumer implements MessageConsumer<OrderEvent> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(OrderUpdatedMessageConsumer.class);
    private final DeliveryService<?> ifoodDeliveryService;

    @RabbitListener(queues = {"${rabbitmq.queue.order.canceled.name}"})
    @Override
    public void getMessage(OrderEvent message){
        LOGGER.info(String.format("Received message on canceledQueue-> %s", message.toString()));
        handleMessage(message);
    }

    private void handleMessage(OrderEvent message) {
        IfoodRequestCancellation cancelDetails = new IfoodRequestCancellation(message.getCancellationCode(), message.getReason());
        ifoodDeliveryService.cancelOrder(message.getOrderId(), cancelDetails);
    }

}
