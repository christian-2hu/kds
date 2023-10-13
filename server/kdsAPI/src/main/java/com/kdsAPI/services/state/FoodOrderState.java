package com.kdsAPI.services.state;

import java.util.List;

import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.repositories.OrderRepository;

public abstract class FoodOrderState {

    protected OrderRepository repository;

    public FoodOrderState(OrderRepository repository) {
        this.repository = repository;
    }

    public abstract List<FoodOrder> getAll();

}
