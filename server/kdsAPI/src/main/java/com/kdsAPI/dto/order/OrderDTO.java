package com.kdsAPI.dto.order;

import java.util.List;

import com.kdsAPI.dto.DTO;
import com.kdsAPI.models.FoodItem;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.order.Order;
import com.kdsAPI.order.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO extends Order implements DTO<FoodOrder>{

    public OrderDTO() {
        super();
    }

    public OrderDTO(Long id, OrderStatus fooOrderStatus) {
        super(id, fooOrderStatus);
    }

    public OrderDTO(Long id, List<FoodItem> foodItem, List<Integer> quantity, OrderStatus fooOrderStatus) {
        super(id, foodItem, quantity, fooOrderStatus);
    }

    @Override
    public FoodOrder convertToDAO() {
        return new FoodOrder(id, foodItem, quantity, foodOrderStatus);
    }
}
