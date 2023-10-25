package com.kdsAPI.models;

import com.kdsAPI.models.foodItem.Item;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FoodItem extends Item {
    public FoodItem(Long id, String name, String unit, Double quantity) {
        super(id, name, unit, quantity);
    }
}
