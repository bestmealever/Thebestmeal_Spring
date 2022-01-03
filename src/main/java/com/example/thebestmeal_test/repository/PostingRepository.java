package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.PostingStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting,Long> {
    //EntityGraph를 아래와 같이 넣으면 "likedFood"를 못찾아옴 -> mypage가 제대로 뜨지 않는다.
    @EntityGraph(attributePaths = {"tags","recommendeds","food","user","likedFood"})
    List<Posting> findAllByUserId(Long id);
    //삭제
//    List<Posting> findAllByStatusIsNull();
//    List<Posting> findAllByStatus(Status status);

    @EntityGraph(attributePaths = {"food","user"})
    List<Posting> findAllByStatus(PostingStatus postingstatus);

//    List<Posting> findAllByPostingStatus(PostingStatus postingstatus);

    //원래의 코드
//    List<Posting> findAllByUserId(Long id);
//    List<Posting> findAllByStatusIsNull();
//    List<Posting> findAllByStatus(String status);


}
