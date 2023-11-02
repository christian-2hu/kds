package com.kdsAPI.repositories;


import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdsAPI.models.FoodOrder;
import com.kdsAPI.models.order.OrderStatus;

public interface OrderRepository extends JpaRepository<FoodOrder, Long>{
   @Query(
    """
        SELECT 
            foodOrder 
        FROM 
            FoodOrder foodOrder 
        WHERE 
            foodOrder.foodOrderStatus != OrderStatus.COMPLETE 
        AND 
            foodOrder.foodOrderStatus != OrderStatus.CANCELED 
        ORDER BY 
            foodOrder.id ASC
    """
    )
    public Page<FoodOrder> findAll(Pageable pageable);

    public Page<FoodOrder> findByFoodOrderStatus(OrderStatus foodOrderStatus, Pageable pageable);

    public FoodOrder findByIfoodOrderId(String ifoodOrderId);
}
