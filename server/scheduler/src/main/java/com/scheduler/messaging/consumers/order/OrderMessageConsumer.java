package com.scheduler.messaging.consumers.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.scheduler.messaging.consumers.MessageConsumer;
import com.scheduler.services.DeliveryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderMessageConsumer implements MessageConsumer<OrderEvent> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(OrderMessageConsumer.class);
    private final DeliveryService<?> ifoodDeliveryService;

    @RabbitListener(queues = {"${rabbitmq.queue.order.updated.name}"})
    @Override
    public void getMessage(OrderEvent message){
        LOGGER.info(String.format("Received message -> %s", message.toString()));
        handleMessage(message);
    }

    private void handleMessage(OrderEvent message) {
        switch(message.getUpdatedOrderStatus()) {
            // TODO: this need a dedicated implementation, as this is a cancelation that came from the client
            case CANCELED:
                break;
            case COMPLETE:
                ifoodDeliveryService.finishOrder(message.getOrderId());
                break;
            case CONFIRMED:
                ifoodDeliveryService.confirmOrder(message.getOrderId());
                break;
            case PREPARING:
                ifoodDeliveryService.prepareOrder(message.getOrderId());
                break;
            default:
                throw new RuntimeException("No such status existes on OrderStatus");
        }
    }

}
