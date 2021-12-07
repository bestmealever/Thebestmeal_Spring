package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.LikedFood;
import com.example.thebestmeal_test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikedFoodRepository extends JpaRepository<LikedFood, Long> {
    Optional<LikedFood> findByUserAndFood(User user, Food food);
    Optional<LikedFood> findByUser_IdAndFood_Id(Long userId, Long FoodId);
    List<LikedFood> findByFood_Id(Long id);

}
