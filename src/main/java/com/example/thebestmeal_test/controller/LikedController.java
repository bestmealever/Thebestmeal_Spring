package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.service.LikedService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikedController {

    private final FoodRepository foodRepository;
    private final LikedService likedService;


    @GetMapping("/liked")
    public List<Food> getFoodList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null) {
            return foodRepository.findTop5ByLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(userDetails.getUser());
        } else {
            return foodRepository.findTop5ByOrderByCntDesc();
        }
    }

    @GetMapping("/food")
    public List<Food> newUpdateFood() {
        return foodRepository.findTop5ByOrderByIdDesc();
    }

    @GetMapping("/allFood")
    public List<Food> viewAllFood() {
        return foodRepository.findAll();
    }

    //좋아요
    @PostMapping("/liked/{id}")
    public Boolean updateLikeFood(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likedService.liked(id, userDetails);
    }

    //좋아요 취소
    @DeleteMapping("/liked/{id}")
    public String deleteLikedFood(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likedService.unLiked(id, userDetails);
        return "좋아요를 취소하셨습니다";
    }


}
