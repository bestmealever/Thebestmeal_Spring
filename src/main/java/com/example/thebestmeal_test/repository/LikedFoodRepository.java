package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.LikedFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedFoodRepository extends JpaRepository<LikedFood, Long> {
}
