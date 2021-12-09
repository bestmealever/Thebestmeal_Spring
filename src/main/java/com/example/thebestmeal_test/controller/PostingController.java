package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.dto.FoodCheckDto;
import com.example.thebestmeal_test.dto.PostDto;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.PostingRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.service.AwsService;
import com.example.thebestmeal_test.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping({"/api"})
@RestController
public class PostingController {

    private final PostingService postingService;
    private final AwsService awsService;
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final FoodRepository foodRepository;

    @Transactional
    @PostMapping({"/post"})
    public String postFood(PostDto postDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        User user = (User) this.userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> {
            return new NullPointerException("그런 사람 없는데요?");
        });
//       food 저장
        String foodImgUrl = this.awsService.uploadFoodImg(postDto.getFoodImgUrl());
        Food food = new Food(postDto, foodImgUrl);
        this.foodRepository.save(food);
        //category 저장
//        List<String> items1 = Arrays.asList(postDto.getPostingTag());
        List<Tag> tags1 = (List) postDto.getPostingEmo().stream().map((tag) -> {
            return new Tag(food, tag, "emotion");
        }).collect(Collectors.toList());
        this.tagRepository.saveAll(tags1);
        //emotion 저장
        List<Tag> tags2 = (List) postDto.getPostingCat().stream().map((tag) -> {
            return new Tag(food, tag, "category");
        }).collect(Collectors.toList());
        this.tagRepository.saveAll(tags2);
        Food food2 = (Food) this.foodRepository.findByName(postDto.getPostingFoodName()).get();
        Posting posting = new Posting(postDto, user, food2);
        this.postingRepository.save(posting);

        return foodImgUrl;

    }

    //controller 분리 시도 중
//    public String postFood(PostDto postDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
//        return postingService.postFoodService(postDto, userDetails);}

    @PostMapping({"/foodcheck"})
    public Boolean foodCheck(@RequestBody FoodCheckDto foodCheckDto) {
        String postingFoodName = foodCheckDto.getPostingFoodName();
        Optional<Food> found = this.foodRepository.findByName(postingFoodName);
        Boolean response = found.isPresent();
        System.out.println(response);
        return response;
    }
}




