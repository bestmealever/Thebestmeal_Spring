package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByTagName(String name);

    List<Tag> findAllByTagNameNotIn(List<String> names);
    List<Tag> findAllByTagNameIn(List<String> names);
}
