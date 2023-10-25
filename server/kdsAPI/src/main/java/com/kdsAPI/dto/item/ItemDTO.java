package com.kdsAPI.dto.item;

import com.kdsAPI.dto.DTO;
import com.kdsAPI.models.FoodItem;
import com.kdsAPI.models.foodItem.Item;

public class ItemDTO extends Item implements DTO<FoodItem>{

    public ItemDTO(Long id, String name, String unit, Double quantity) {
        super(id, name, unit, quantity);
    }

    @Override
    public FoodItem convertToDAO() {
        return new FoodItem(id, name, unit, quantity);
    }
    
}
