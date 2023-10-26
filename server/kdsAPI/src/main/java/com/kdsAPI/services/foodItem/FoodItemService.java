package com.kdsAPI.services.foodItem;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kdsAPI.dto.item.ItemDTO;
import com.kdsAPI.models.FoodItem;
import com.kdsAPI.repositories.FoodItemRepository;
import com.kdsAPI.services.AbstractService;

@Service
public class FoodItemService extends AbstractService<FoodItem, ItemDTO> {

    public FoodItemService(FoodItemRepository repository) {
        super(repository);
    }

    // TODO: this is way too ugly, fix this
    @Override
    public FoodItem save(ItemDTO dto) {
        FoodItemRepository repository = (FoodItemRepository)this.repository;
        List<FoodItem> foundItems = repository.findByName(dto.getName());
        return foundItems.size() == 0 ? repository.save(dto.convertToDAO()) : foundItems.get(0);
    }
}
