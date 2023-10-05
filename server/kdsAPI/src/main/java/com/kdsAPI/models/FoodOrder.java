package com.kdsAPI.models;

import java.util.List;

import com.kdsAPI.order.Order;
import com.kdsAPI.order.OrderStatus;

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

    public FoodOrder(Long id, List<FoodItem> foodItem, List<Integer> quantity, OrderStatus fooOrderStatus) {
        super(id, foodItem, quantity, fooOrderStatus);
    }

}
