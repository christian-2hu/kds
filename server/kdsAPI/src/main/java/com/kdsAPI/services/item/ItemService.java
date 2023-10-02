package com.kdsAPI.services.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.kdsAPI.dto.item.ItemDTO;
import com.kdsAPI.models.FoodItem;
import com.kdsAPI.services.AbstractService;

@Service
public class ItemService extends AbstractService<FoodItem, ItemDTO>{

    public ItemService(JpaRepository<FoodItem, Long> repository) {
        super(repository);
    }

    @Override
    public FoodItem save(ItemDTO order) {
        return repository.save(order.convertToDAO());
    }

}
