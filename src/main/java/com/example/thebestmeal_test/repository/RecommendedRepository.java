package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Recommended;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendedRepository extends JpaRepository<Recommended, Long> {
    List<Recommended> findAllByUserId(Long id);
}
