package com.kdsAPI.repositories;


import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.models.order.OrderStatus;

public interface OrderRepository extends JpaRepository<FoodOrder, Long>{
   @Query("SELECT foodOrder from FoodOrder foodOrder WHERE foodOrder.foodOrderStatus != OrderStatus.COMPLETE ORDER BY foodOrder.id DESC")
    public Page<FoodOrder> findAll(Pageable pageable);

    public Page<FoodOrder> findByFoodOrderStatus(OrderStatus foodOrderStatus, Pageable pageable);

    public FoodOrder findByIfoodOrderId(String ifoodOrderId);
}
