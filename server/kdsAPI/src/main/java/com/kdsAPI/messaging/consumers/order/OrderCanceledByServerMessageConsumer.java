package com.kdsAPI.messaging.consumers.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.messaging.consumers.MessageConsumer;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.models.OrderDetails;
import com.kdsAPI.services.AbstractOrderService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderCanceledByServerMessageConsumer implements MessageConsumer<OrderDetails> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(OrderCanceledByServerMessageConsumer.class);
    private final AbstractOrderService ifoodOrderService;

    @RabbitListener(queues = {"${rabbitmq.queue.order.canceled.byServer}"})
    @Override
    public void getMessage(OrderDetails message){
        if(message.getCancellationCode() != null || message.getReason() != null) {
            return;
        }
        LOGGER.info(String.format("Received message on canceled -> %s", message.toString()));
        handleMessage(message);
    }
    
    private void handleMessage(OrderDetails message) {
        FoodOrder foundOrder = ifoodOrderService.getByOrderId(message.getOrderId());
        foundOrder.setFoodOrderStatus(message.getUpdatedOrderStatus());
        OrderDTO dto = new OrderDTO();
        dto.convertToDTO(foundOrder);
        ifoodOrderService.update(dto);
        LOGGER.info(String.format("Updated to CANCELED order by id %s", foundOrder.getId()));
    }
}