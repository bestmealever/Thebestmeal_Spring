package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.Recommended;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.dto.MyPageDto;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.PostingRepository;
import com.example.thebestmeal_test.repository.RecommendedRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecommendedService {

    private final RecommendedRepository recommendedRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final PostingRepository postingRepository;

    public void saveRecommendedInfo(UserDetailsImpl userDetails, String foodName) {
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        Optional<Food> food = foodRepository.findByName(foodName);
        Recommended recommended = new Recommended(food.get(), user.get());
        recommendedRepository.save(recommended);
    }

    public MyPageDto toMyPageInfo(UserDetailsImpl userDetails) {
        List<Food> foods = new ArrayList<>();
        List<Posting> postings = postingRepository.findAllByUserId(userDetails.getUser().getId());

        List<Recommended> recommendedList = recommendedRepository.findAllByUserId(userDetails.getUser().getId());
        for (Recommended recommended : recommendedList) {
            foods.add(recommended.getFood());
        }


        System.out.println(foods);
        MyPageDto dto = new MyPageDto(userDetails.getUser(), foods, recommendedList, postings);
        System.out.println(dto);
        return dto;
    }
}
