package com.kdsAPI.item;

import com.kdsAPI.dto.item.ItemDTO;

public class ItemFactory {
    public Item createItem(Long id, String item, String recipe) {
        return new ItemDTO(id, item, recipe);
    }

    public Item createItem() {
        return new ItemDTO();
    }
}
