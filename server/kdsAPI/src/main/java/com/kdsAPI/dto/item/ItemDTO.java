package com.kdsAPI.dto.item;

import java.util.HashMap;
import java.util.Map;

import com.kdsAPI.dto.DTO;
import com.kdsAPI.item.Item;
import com.kdsAPI.models.FoodItem;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ItemDTO implements DTO<FoodItem>, Item {
    private final String notEmptyMessage = "This field cannot be empty";

    private Long id;
    @NotEmpty(message = notEmptyMessage)
    private Map<String, Integer> item;

    public ItemDTO() {
        this.item = new HashMap<>();
    }

    @Override
    public FoodItem convertToDAO() {
        return new FoodItem(id, item);
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
