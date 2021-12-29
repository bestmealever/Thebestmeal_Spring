package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {
}