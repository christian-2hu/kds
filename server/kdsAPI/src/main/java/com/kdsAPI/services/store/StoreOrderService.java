package com.kdsAPI.services.store;

import org.springframework.stereotype.Service;

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.repositories.OrderRepository;
import com.kdsAPI.services.AbstractService;

@Service
public class StoreOrderService extends AbstractService<FoodOrder, OrderDTO> {

    public StoreOrderService(OrderRepository repository) {
        super(repository);
    }

    @Override
    public FoodOrder save(OrderDTO order) {
        return repository.save(order.convertToDAO());
    }

}
