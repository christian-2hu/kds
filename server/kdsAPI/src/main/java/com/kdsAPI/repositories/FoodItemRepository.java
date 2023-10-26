package com.kdsAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdsAPI.models.FoodItem;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long>{
    public List<FoodItem> findByName(String name);
}
