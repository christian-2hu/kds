package com.kdsAPI.dto.order;

import java.util.ArrayList;
import java.util.List;

import com.kdsAPI.dto.DTO;
import com.kdsAPI.models.FoodItem;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.order.Order;
import com.kdsAPI.order.OrderStatus;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class OrderDTO implements DTO<FoodOrder>, Order{

    private final String notEmptyMessage = "This field cannot be empty";
    private final String nullMessage = "This field should be empty";
    
    @Null(message = nullMessage)
    private Long id;
    @NotEmpty(message = notEmptyMessage)
    private List<FoodItem> foodItem;
    @NotNull(message = notEmptyMessage)
    private OrderStatus foodOrderStatus;

    public OrderDTO() {
        this.foodItem = new ArrayList<>();
        this.foodOrderStatus = OrderStatus.WAITING;
    }

    @Override
    public void addFoodItem(FoodItem foodItem) {
        this.foodItem.add(foodItem);
    }
    @Override
    public void deleteFoodItem(FoodItem foodItem) {
        this.foodItem.remove(foodItem);
    }

    @Override
    public FoodOrder convertToDAO() {
        return new FoodOrder(id, foodItem, foodOrderStatus);
    }
}
