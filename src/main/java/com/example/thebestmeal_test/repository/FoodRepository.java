package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
//    Food findByName(String name); //Optional 로 바꿈.
    Optional <Food> findByName(String name);

}
