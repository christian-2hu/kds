package com.kdsAPI.services.state;


import java.util.List;

import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.order.OrderStatus;
import com.kdsAPI.repositories.OrderRepository;

public class FoodOrderCompleteStateService extends FoodOrderState {

    public FoodOrderCompleteStateService(OrderRepository repository) {
        super(repository);
    }

    @Override
    public List<FoodOrder> getAll() {
        return this.repository.findByFoodOrderStatus(OrderStatus.COMPLETE);
    }

}
