package com.kdsAPI.services.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.repositories.OrderRepository;
import com.kdsAPI.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreOrderService extends OrderService {

    @Override
    public FoodOrder saveOrder(OrderDTO order) {
        return orderRepository.save(order.convertToDAO());
    }

    @Override
    @Autowired
    public void setOrderRepository(OrderRepository repository) {
        this.orderRepository = repository;
    }
}
