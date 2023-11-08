package com.kdsAPI.messaging.producers.order;

import com.kdsAPI.models.OrderDetails;
import com.kdsAPI.models.order.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderEvent extends OrderDetails{
    public OrderEvent(String orderId, OrderStatus orderStatus) {
        super(orderId, orderStatus);
    }

    public OrderEvent(String orderid, OrderStatus orderStatus, String cancellationCode, String reason) {
        super(orderid, orderStatus, cancellationCode, reason);
    }
}
