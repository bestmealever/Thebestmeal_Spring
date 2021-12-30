package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}