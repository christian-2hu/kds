package com.kdsAPI.item;

import com.kdsAPI.dto.item.ItemDTO;

public class ItemFactory {
    public Item createItem(Long id, String item) {
        return new ItemDTO(id, item);
    }

    public Item createItem() {
        return new ItemDTO();
    }
}
