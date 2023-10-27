package com.scheduler.messaging.consumers.order;

import com.scheduler.models.ifood.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEvent {
    private String orderId;
    private OrderStatus updatedOrderStatus;
}
