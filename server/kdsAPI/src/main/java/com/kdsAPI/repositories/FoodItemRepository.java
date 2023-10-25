package com.kdsAPI.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdsAPI.models.FoodItem;

@Repository
@Qualifier("foodItemRepository")
public interface FoodItemRepository extends JpaRepository<FoodItem, Long>{

}
