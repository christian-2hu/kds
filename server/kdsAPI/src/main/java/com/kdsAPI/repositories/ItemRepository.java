package com.kdsAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdsAPI.models.FoodItem;

public interface ItemRepository extends JpaRepository<FoodItem, Long>{

}
