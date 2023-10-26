package com.kdsAPI.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdsAPI.models.FoodItem;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long>{
    public Optional<FoodItem> findByName(String name);
}
