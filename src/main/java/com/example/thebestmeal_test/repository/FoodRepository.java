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
    //test
//   <Food> findById(Long id);
    //foodDupCheck용
    Optional<Food> findByName(String name);

    @EntityGraph(attributePaths = {"tags","likedFood","recommendeds", "posting"})
    List<Food> findTop12ByOrderByCntDesc();

    //liked 변경 코드 테스트중 - 같은 쿼리의 인자를 다르게 할 방법이 없을까?
//    List<Food> findTop12ByOrderByCntDesc(User user);

    @EntityGraph(attributePaths = {"tags","likedFood","recommendeds", "posting"})
    List<Food> findTop12ByLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(User user);
    List<Food> findAllByNameIn(List<String> name);
    List<Food> findNameAndImageUrlByNameIn(List<String> name);

    //테스트용 코드
//    List<Food> findAllByPostingStatus(PostingStatus Accepted);

    List<Food> findTop12ByPostingIsNullOrPostingStatusOrderByCntDesc(PostingStatus Accepted);

//    List<Food> findTop12ByLikedFoodIsNullOrLikedFoodUserAndPostingIsNullOrPostingStatusOrderByCntDesc(PostingStatus Accepted);
//    List<Food> findTop12ByOrderByCntDescByPostingStatus(PostingStatus Accepted);

    //테스트중 기준 테이블을 Posting이 아닌 Food 로 바꿔보기
    //(1) Food and 문
//    List<Food> findTop12ByLikedFoodIsNullOrLikedFoodUserAndPostingIsNullOrPostingStatusOrderByCntDesc(User user, PostingStatus Accepted);

    //(@) Food Or 문
//    List<Food> findTop12ByLikedFoodIsNullOrLikedFoodUserOrPostingIsNullOrPostingStatusOrderByCntDesc(User user, PostingStatus Accepted);

    // Posting
    List<Food> findTop12ByPostingIsNullOrPostingStatusAndLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(User user, PostingStatus Accepted);

    //PostingRepository에서 가져오는 방법이 있을까..?
}