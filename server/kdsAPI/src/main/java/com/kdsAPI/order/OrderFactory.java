package com.kdsAPI.order;

import com.kdsAPI.dto.order.OrderDTO;

public class OrderFactory {
    public Order creatOrder() {
        return new OrderDTO();
    }
}
