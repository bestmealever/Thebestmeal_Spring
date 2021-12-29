package com.example.thebestmeal_test.repository;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.PostingStatus;
import com.example.thebestmeal_test.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    //foodDupCheck용
    Optional<Food> findByName(String name);

    @EntityGraph(attributePaths = {"tags","likedFood","recommendeds", "posting"})
    //findAll테스트용
    List<Food> findTop12ByPostingIsNullOrPostingStatusIs(PostingStatus Accepted);

//    List<Food> findAllByPostingIsNullOrPostingStatusIs(PostingStatus Accepted);

    @EntityGraph(attributePaths = {"tags","likedFood","recommendeds", "posting"})
    List<Food> findTop12ByPostingIsNullOrPostingStatusOrderByCntDesc(PostingStatus Accepted);

    List<Food> findAllByNameIn(List<String> name);
    List<Food> findNameAndImageUrlByNameIn(List<String> name);


    //PostingStatus 추가 전 사용했던 코드
//    List<Food> findTop12ByLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(User user);
}