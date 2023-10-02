package com.kdsAPI.dto.item;

import com.kdsAPI.dto.DTO;
import com.kdsAPI.item.Item;
import com.kdsAPI.models.FoodItem;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements DTO<FoodItem>, Item {
    private final String notEmptyMessage = "This field cannot be empty";

    private Long id;
    @NotEmpty(message = notEmptyMessage)
    private String item;

    @Override
    public FoodItem convertToDAO() {
        return new FoodItem(id, item);
    }

}
