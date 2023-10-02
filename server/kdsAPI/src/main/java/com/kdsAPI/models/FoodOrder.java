package com.kdsAPI.models;

import java.util.ArrayList;
import java.util.List;

import com.kdsAPI.order.Order;
import com.kdsAPI.order.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
public class FoodOrder implements Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<FoodItem> foodItem;
    private OrderStatus foodOrderStatus;

    public FoodOrder() {
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


}
