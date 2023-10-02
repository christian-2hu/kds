package com.kdsAPI.item;

import java.util.Map;

import com.kdsAPI.dto.item.ItemDTO;

public class ItemFactory {
    public Item createItem(Long id, Map<String, Integer> item) {
        return new ItemDTO(id, item);
    }

    public Item createItem() {
        return new ItemDTO();
    }
}
