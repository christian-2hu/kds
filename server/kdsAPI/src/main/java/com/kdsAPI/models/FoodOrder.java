package com.kdsAPI.models;

import java.util.Date;
import java.util.List;

import com.kdsAPI.models.order.Order;
import com.kdsAPI.models.order.OrderStatus;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FoodOrder extends Order {
    public FoodOrder() {
        super();
    }

    public FoodOrder(Long id, OrderStatus fooOrderStatus) {
        super(id, fooOrderStatus);
    }

    public FoodOrder(
        Long id, 
        String costumerName, 
        List<FoodItem> order, 
        OrderStatus orderStatus, 
        String ifoodOrderId, 
        Date createdAt, 
        String observation
    ) {
        super(id, costumerName, order, orderStatus, ifoodOrderId, createdAt, observation);
    }


}
