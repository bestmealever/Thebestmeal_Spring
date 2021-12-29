package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(String name);

    @EntityGraph(attributePaths = {"tags","likedFood","recommendeds"})
    List<Food> findTop5ByOrderByCntDesc();

    @EntityGraph(attributePaths = {"tags","likedFood","recommendeds"})
    List<Food> findTop5ByLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(User user);

    List<Food> findAllByNameIn(List<String> name);
    List<Food> findNameAndImageUrlByNameIn(List<String> name);

    @EntityGraph(attributePaths = {"tags","likedFood","recommendeds"})
    List<Food> findTop5ByOrderByIdDesc();

    @EntityGraph(attributePaths = {"tags","likedFood","recommendeds"})
    List<Food> findAll();
}