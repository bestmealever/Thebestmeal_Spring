package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
