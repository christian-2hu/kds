package com.kdsAPI.services.store;

import org.springframework.stereotype.Service;

import com.kdsAPI.repositories.OrderRepository;
import com.kdsAPI.services.AbstractOrderService;

@Service
public class StoreOrderService extends AbstractOrderService {

    public StoreOrderService(OrderRepository repository) {
        super(repository);
    }
}
