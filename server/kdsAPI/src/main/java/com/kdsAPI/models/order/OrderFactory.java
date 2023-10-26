package com.kdsAPI.models.order;

import com.kdsAPI.dto.order.OrderDTO;

public class OrderFactory {
    public Order creatOrder() {
        return new OrderDTO();
    }
}
