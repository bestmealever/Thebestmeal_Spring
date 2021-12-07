package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(String name);
    @EntityGraph(attributePaths = {"tags","likedFood"})
    List<Food> findTop12ByOrderByLikedFoodDesc();
    @EntityGraph(attributePaths = {"tags","likedFood"})
    List<Food> findTop12ByLikedFoodIsNullOrLikedFoodUserOrderByLikedFoodDesc(User user);

    List<Food> findAllByNameIn(List<String> name);
    List<Food> findNameAndImageUrlByNameIn(List<String> name);
}