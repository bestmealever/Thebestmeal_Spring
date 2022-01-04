package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.PostingStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting,Long> {

//    @EntityGraph(attributePaths = {"tags","recommendeds","food","user","likedFood"})
    List<Posting> findAllByUserId(Long id);

//    @EntityGraph(attributePaths = {"food","user"})
    List<Posting> findAllByStatus(PostingStatus postingstatus);

}
