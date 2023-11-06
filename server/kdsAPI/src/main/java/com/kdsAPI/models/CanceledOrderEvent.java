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
public class CanceledOrderEvent {
    private String orderId;
    private OrderStatus updatedOrderStatus;
    // TODO: transform this into an enum
    private String cancellationCode;
    private String reason;
}
