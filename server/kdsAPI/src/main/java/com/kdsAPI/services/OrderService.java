package com.kdsAPI.services;

import java.util.List;

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.exception.NotFoundException;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.repositories.OrderRepository;

public abstract class OrderService {

    protected OrderRepository orderRepository;

    public List<FoodOrder> getOrders() {
        return orderRepository.findAll();
    }

    public abstract FoodOrder saveOrder(OrderDTO order);

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public FoodOrder updateOrder(OrderDTO order) {
        return orderRepository.save(order.convertToDAO());
    }
    public FoodOrder getOrderById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new NotFoundException());
    }

    public abstract void setOrderRepository(OrderRepository repository);
}
