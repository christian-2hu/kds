package com.kdsAPI.models;

import java.util.HashMap;
import java.util.Map;

import com.kdsAPI.item.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
public class FoodItem implements Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Map<String, Integer> item;

    public FoodItem() {
        this.item = new HashMap<>();
    }

    @Override
    public void setItem(String item, Integer quantity) {
        this.item.put(item, quantity);
    }

    @Override
    public void removeItem(String item) {
        this.item.remove(item);
    }
}
