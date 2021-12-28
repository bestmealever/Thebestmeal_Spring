package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}