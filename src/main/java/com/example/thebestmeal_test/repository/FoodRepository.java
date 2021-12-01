package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Food findByName(String name);
    List<Food> findTop9ByOrderByIdAsc();
}
