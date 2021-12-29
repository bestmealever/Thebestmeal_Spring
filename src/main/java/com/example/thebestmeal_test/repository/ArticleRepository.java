package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @EntityGraph(attributePaths = {"vote","comments","user","tags"})
    List<Article> findAll();
}