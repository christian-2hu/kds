package com.kdsAPI.models;

import com.kdsAPI.models.order.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetails extends CanceledOrderDetails{
    protected String orderId;
    protected OrderStatus updatedOrderStatus;

    public OrderDetails(String orderid, OrderStatus orderStatus, String cancellationCode, String reason) {
        this.orderId = orderid;
        this.updatedOrderStatus = orderStatus;
        this.cancellationCode = cancellationCode;
        this.cancellationCode = cancellationCode;
        this.reason = reason;
    }
    
}
