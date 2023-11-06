package com.kdsAPI.services.ifood;

import org.springframework.stereotype.Service;

import com.kdsAPI.dto.order.OrderDTO;
import com.kdsAPI.exception.NotFoundException;
import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.repositories.OrderRepository;
import com.kdsAPI.services.AbstractOrderService;


@Service
public class IfoodOrderService extends AbstractOrderService {

    public IfoodOrderService(OrderRepository repository) {
        super(repository);
    }

    

    @Override 
    public FoodOrder update(OrderDTO dto) {
        FoodOrder updatedOrder = findByIfoodOrderId(dto.getIfoodOrderId());
        dto.setId(updatedOrder.getId());
        return repository.save(dto.convertToDAO());
    }

    private FoodOrder findByIfoodOrderId(String findByIfoodOrderId) {
        OrderRepository repository = (OrderRepository) this.repository;
        return repository.findByIfoodOrderId(findByIfoodOrderId)
            .orElseThrow(() -> new NotFoundException());
    }
}
