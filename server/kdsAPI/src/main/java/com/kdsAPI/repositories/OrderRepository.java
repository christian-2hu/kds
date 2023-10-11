package com.kdsAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdsAPI.models.FoodOrder;

public interface OrderRepository extends JpaRepository<FoodOrder, Long>{
   @Query("SELECT foodOrder from FoodOrder foodOrder ORDER BY foodOrder.id ASC")
    public List<FoodOrder> findAll();
}
