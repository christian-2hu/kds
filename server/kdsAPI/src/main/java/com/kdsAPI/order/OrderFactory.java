package com.kdsAPI.order;

import com.kdsAPI.dto.order.OrderDTO;

public class OrderFactory {
    public Order createOrder(Long id, String order) {
        return new OrderDTO(id, order);
    }

    public Order creatOrder() {
        return new OrderDTO();
    }
}
