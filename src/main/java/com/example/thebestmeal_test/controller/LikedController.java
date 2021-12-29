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


    //변경 중: 메인에 PostingFood 가 온다면 Declined 된 아이는 오면 안됨.
    @GetMapping("/liked")
    public List<Food> getFoodList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails != null) {
            return foodRepository.findTop12ByPostingIsNullOrPostingStatusAndLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(userDetails.getUser(), PostingStatus.Accepted);
            //getUser에서 오류남
//            return foodRepository.findTop12ByPostingIsNullOrPostingStatusAndLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(userDetails.getUser(), PostingStatus.Accepted);
//            return foodRepository.findTop12ByLikedFoodIsNullOrLikedFoodUserAndPostingIsNullOrPostingStatusOrderByCntDesc(userDetails.getUser(), PostingStatus.Accepted);
//            return foodRepository.findTop12ByLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(userDetails.getUser());
//            return foodRepository.findTop12ByLikedFoodIsNullOrLikedFoodUserAndPostingIsNullOrPostingStatusOrderByCntDesc(userDetails.getUser(), PostingStatus.Accepted);

        } else {
            // return foodRepository.findTop12ByOrderByCntDesc();
            //포스팅 추가
            return foodRepository.findTop12ByPostingIsNullOrPostingStatusOrderByCntDesc(PostingStatus.Accepted);
        }
    }

    // 테스트 - AcceptedFood만 출력. 예상 - Food 형태 /
    // 로그인 된 상태에는 top12 likedfood를 (유저가 좋아요 하지 않은)
    // 로그인이 되지 않은 상태에서는 (PostingStatus 가 Accepted인 것을 모두 가져오는 쿼리)
//    @GetMapping("/onlyreadyfoodlist")
//    public List<Food> getreadyFoodList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        if (userDetails != null) {
////            return foodRepository.findTop12ByLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(userDetails.getUser());
////            return foodRepository.findTop12ByLikedFoodIsNullOrLikedFoodUserAndPostingIsNullOrPostingStatusOrderByCntDesc(PostingStatus.Accepted);
//            // or -> 치킨 두개 나옴.
////            return foodRepository.findTop12ByLikedFoodIsNullOrLikedFoodUserOrPostingIsNullOrPostingStatusOrderByCntDesc(userDetails.getUser(), PostingStatus.Accepted);
////            return foodRepository.findTop12ByPostingIsNullOrPostingStatusAndLikedFoodIsNullOrLikedFoodUserOrderByCntDesc(userDetails.getUser(), PostingStatus.Accepted);
//            return foodRepository.findTop12ByLikedFoodIsNullOrLikedFoodUserAndPostingIsNullOrPostingStatusOrderByCntDesc(userDetails.getUser(), PostingStatus.Accepted);
//
//            //안됨 .
////            User user = userDetails.getUser();
////            List<Food> findTop12ByLikedFoodIsNullOrLikedFoodUserAndPostingIsNullOrPostingStatusOrderByCntDesc(user, PostingStatus.Accepted);
//
//        } else {
//            //에러 발생
//            return foodRepository.findTop12ByPostingIsNullOrPostingStatusOrderByCntDesc(PostingStatus.Accepted);
//
//        }
//    }

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
