package com.kdsAPI.services.state;

import java.util.List;

import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.repositories.OrderRepository;

public class FoodOrderWaitingStateService extends FoodOrderState {

    public FoodOrderWaitingStateService(OrderRepository repository) {
        super(repository);
    }

    @Override
    public List<FoodOrder> getAll() {
        return this.repository.findAll();
    }
}
