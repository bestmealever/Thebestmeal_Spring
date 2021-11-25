package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
