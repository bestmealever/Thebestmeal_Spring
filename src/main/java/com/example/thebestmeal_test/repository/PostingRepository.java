package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.PostingStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting,Long> {

// mypage 용도
@EntityGraph(attributePaths = {"food", "user"})
List<Posting> findAllByUserId(Long id);

//Unable to locate Attribute  with the the given name [likedFood] on this ManagedTyp
// @EntityGraph(attributePaths = {"food", "user", "tags", "likedFood", "recommendeds"})
// admin 용도
//  @EntityGraph(attributePaths = {"food","user"})
    List<Posting> findAllByStatus(PostingStatus postingstatus);

}
