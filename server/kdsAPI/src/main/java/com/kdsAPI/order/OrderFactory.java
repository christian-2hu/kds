package com.kdsAPI.order;
import java.util.List;

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.models.FoodItem;

public class OrderFactory {
    public Order createOrder(Long id, List<FoodItem> foodItem) {
        return new OrderDTO(id, foodItem, OrderStatus.WAITING);
    }

    public Order creatOrder() {
        return new OrderDTO();
    }
}
