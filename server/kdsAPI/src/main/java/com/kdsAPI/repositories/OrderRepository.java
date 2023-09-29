package com.kdsAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdsAPI.models.FoodOrder;

public interface OrderRepository extends JpaRepository<FoodOrder, Long>{

}
