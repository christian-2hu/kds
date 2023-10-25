package com.kdsAPI.services.foodItem;

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

    @Override
    public FoodItem save(ItemDTO dto) {
        return repository.save(dto.convertToDAO());
    }
}
