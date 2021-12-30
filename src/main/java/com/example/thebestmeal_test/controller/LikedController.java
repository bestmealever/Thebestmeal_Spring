package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.PostingStatus;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.PostingRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.service.LikedService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikedController {

    private final FoodRepository foodRepository;
    private final LikedService likedService;
    private final PostingRepository postingRepository;


    //메인에 올라간 음식 중 (추천하기로 받아진 음식은) Admin- Declined 안됨.
    //food 클래스 형태의 foods 라는 객체 리스트 만든다.
    //foods 리스트에서 food를 for 문을 돌아 꺼내온다. user의 Id 와 동일한지 비교한다.
    //liked의 기본값은 false 지만 해당 userId와 likedFood의 userId가 일치한다면, setLiked를 True라고 한다.
    @GetMapping("/liked")
    public List<Food> getFoodList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null) {
            List<Food> foods =  foodRepository.findFirst12ByPostingIsNullOrPostingStatusIsOrderByCntDesc(PostingStatus.Accepted);
            for(Food food : foods){
                if(food.getLikedFood().stream().filter( e-> e.getUser().getId().equals(userDetails.getUser().getId())).count() > 0 ){
                    food.setLiked(true);
                }
            }
            return foods;

        } else {
            return foodRepository.findTop12ByPostingIsNullOrPostingStatusOrderByCntDesc(PostingStatus.Accepted);
        }
    }


    //좋아요
    @PostMapping("/liked/{id}")
    public Boolean updateLikeFood(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likedService.liked(id, userDetails);
    }

    //좋아요 취소
    @PostMapping("/unliked/{id}")
    public String deleteLikedFood(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likedService.unLiked(id, userDetails);
        return "좋아요를 취소하셨습니다";
    }
}
