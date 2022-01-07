package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Recommended;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendedRepository extends JpaRepository<Recommended, Long> {
    @EntityGraph(attributePaths = {"food", "user","tags","likedFood","recommendeds"})
    List<Recommended> findAllByUserId(Long id);
}
