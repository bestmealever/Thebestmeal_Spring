package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting,Long> {
    List<Posting> findAllByUserId(Long id);
}
