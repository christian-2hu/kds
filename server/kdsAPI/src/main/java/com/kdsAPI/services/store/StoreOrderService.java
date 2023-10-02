package com.kdsAPI.services.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.services.AbstractService;

@Service
public class StoreOrderService extends AbstractService<FoodOrder, OrderDTO> {

    public StoreOrderService(JpaRepository<FoodOrder, Long> repository) {
        super(repository);
    }

    @Override
    public FoodOrder save(OrderDTO order) {
        return repository.save(order.convertToDAO());
    }

}
