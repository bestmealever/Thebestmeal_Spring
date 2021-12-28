package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.PostingStatus;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.service.LikedService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikedController {

    private final FoodRepository foodRepository;
    private final LikedService likedService;

    //원래 코드
    @GetMapping("/liked")
    public List<Food> getFoodList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null) {
            //likedFood가 null이거나, likedFoodUser가 있는 애 중에서 top 12
            return foodRepository.findTop12ByLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(userDetails.getUser());
            //Cnt 순으로만 보여줌.
        } else {
            return foodRepository.findTop12ByOrderByCntDesc();
        }

          //원본 코드

        //테스트 중 - 인자가 다르게 같은 쿼리를 쓸 수 있나?
//            return foodRepository.findTop12ByOrderByCntDesc(userDetails.getUser());
    }

    // 테스트 - AcceptedFood만 출력. 예상 - Food 형태 /
    // 로그인 된 상태에는 top12 likedfood를 (유저가 좋아요 하지 않은)
    // 로그인이 되지 않은 상태에서는 (PostingStatus 가 Accepted인 것을 모두 가져오는 쿼리)
    @GetMapping("/onlyreadyfoodlist")
    public List<Food> getreadyFoodList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            return foodRepository.findTop12ByLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(userDetails.getUser());
        } else {
            //테스트 - PostingStatus가 Accepted인 애들.
            return foodRepository.findAllByPostingStatus(PostingStatus.Accepted);
            //에러 발생
//            return foodRepository.findTop12ByOrderByCntDescByPostingStatus(PostingStatus.Accepted);
        }
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
