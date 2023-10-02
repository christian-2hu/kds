package com.kdsAPI.order;

import java.util.List;

import com.kdsAPI.models.FoodItem;

public interface Order {
    public OrderStatus getFoodOrderStatus();
    public void setFoodOrderStatus(OrderStatus orderStatus);
    public List<FoodItem> getFoodItem();
    public void setFoodItem(List<FoodItem> foodItem);
    public void addFoodItem(FoodItem foodItem);
    public void deleteFoodItem(FoodItem foodItem);
}
