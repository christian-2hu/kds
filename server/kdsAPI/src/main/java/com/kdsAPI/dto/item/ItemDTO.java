package com.kdsAPI.dto.item;

import com.kdsAPI.dto.DTO;
import com.kdsAPI.models.FoodItem;
import com.kdsAPI.models.foodItem.Item;

public class ItemDTO extends Item implements DTO<FoodItem>{

    public ItemDTO(Long id, String name, String unit, Double quantity) {
        super(id, name, unit, quantity);
    }

    public ItemDTO() {
        super();
    }

    @Override
    public FoodItem convertToDAO() {
        return new FoodItem(id, name, unit, quantity);
    }

    @Override
    public DTO<FoodItem> convertToDTO(FoodItem dao) {
        if(dao == null) {
            throw new NullPointerException("FoodItem dao cannot be null");
        }
        this.id = dao.getId();
        this.name = dao.getName();
        this.quantity = dao.getQuantity();
        this.unit = dao.getUnit();
        return this;
    }
    
}
