package com.kdsAPI.messaging.producers.order;

import com.kdsAPI.models.order.OrderStatus;

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
