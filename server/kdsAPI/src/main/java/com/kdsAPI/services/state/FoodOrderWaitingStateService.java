package com.kdsAPI.services.state;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.repositories.OrderRepository;

public class FoodOrderWaitingStateService extends FoodOrderState {

    public FoodOrderWaitingStateService(OrderRepository repository) {
        super(repository);
    }

    @Override
    public List<FoodOrder> getAll(Integer pageNumber, Integer pageSize) {
        setFoodOrderPage(pageNumber, pageSize);
        return this.foodOrderPage.getContent();
    }

    private void setFoodOrderPage(Integer pageNumber, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        this.foodOrderPage = this.repository.findAll(paging);
    }
}
