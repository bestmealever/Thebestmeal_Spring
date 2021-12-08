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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping({"/api"})
@RestController
public class PostingController {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final TagRepository tagRepository;
    private final PostingService postingService;
    private final AwsService awsService;

    @Transactional
    @PostMapping({"/post"})
    public void postFood(PostDto postDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        User user = (User)this.userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> {
            return new NullPointerException("그런 사람 없는데요?");
        });
//       food 저장
        String foodImgUrl = this.awsService.uploadFoodImg(postDto.getFoodImgUrl());
        Food food = new Food(postDto, foodImgUrl);
        this.foodRepository.save(food);
        //category 저장
//        List<String> items1 = Arrays.asList(postDto.getPostingTag());
        List<Tag> tags1 = (List)postDto.getPostingEmo().stream().map((tag) ->
        {
            return new Tag(food, tag, "emotion");
        }).collect(Collectors.toList());
        this.tagRepository.saveAll(tags1);
        //emotion 저장
        List<Tag> tags2 = (List) postDto.getPostingCat().stream().map((tag) -> {
            return new Tag(food, tag, "category");
        }).collect(Collectors.toList());
        this.tagRepository.saveAll(tags2);
        //posting 저장 (foodId, userId, post 내용 저장)
        //.get() -> 그냥 Food 클래스의.. null check(Optional 객체로 한번 감싸짐) 이렇게 쓰면
        //Optional null이 get()
        Food food2 = (Food)this.foodRepository.findByName(postDto.getPostingFoodName()).get();
        Posting posting = new Posting(postDto, user, food2);
        this.postingRepository.save(posting);

    }

    @PostMapping({"/foodcheck"})
    public Boolean foodCheck(@RequestBody FoodCheckDto foodCheckDto) {
        String postingFoodName = foodCheckDto.getPostingFoodName();
        Optional<Food> found = this.foodRepository.findByName(postingFoodName);
        Boolean response = found.isPresent();
        System.out.println(response);
        return response;
    }

    public PostingController(final PostingRepository postingRepository, final UserRepository userRepository, final FoodRepository foodRepository, final TagRepository tagRepository, final PostingService postingService, final AwsService awsService) {
        this.postingRepository = postingRepository;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
        this.tagRepository = tagRepository;
        this.postingService = postingService;
        this.awsService = awsService;
    }

}

