package com.kdsAPI.services.state;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.repositories.OrderRepository;
import com.kdsAPI.responses.PaginationResponse;

public abstract class FoodOrderState {

    protected OrderRepository repository;
    protected Page<FoodOrder> foodOrderPage;

    public FoodOrderState(OrderRepository repository) {
        this.repository = repository;
    }

    public abstract List<FoodOrder> getAll(Integer pageNumber, Integer pageSize);

    public PaginationResponse getPaginationResponse(Integer currentPage) {
        return this.foodOrderPage != null ? new PaginationResponse(this.foodOrderPage.getTotalElements(), this.foodOrderPage.getTotalPages(), currentPage)
        : null;
    }

}
