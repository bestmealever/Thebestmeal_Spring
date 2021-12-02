package com.example.thebestmeal_test.controller;
import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.dto.PostDto;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.PostingRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PostingController {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final TagRepository tagRepository;

    private final PostingService postingService;


    @PostMapping("/post")
    public void postFood(@RequestBody PostDto postDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("그런 사람 없는데요?")
        );
       //food 저장
        Food food = new Food(postDto);
        foodRepository.save(food);
        //category 저장
//        List<String> items1 = Arrays.asList(postDto.getPostingTag());
        List<Tag> tags1 = postDto.getPostingEmo().stream().map(tag -> new Tag(food, tag, "emotion")).collect(Collectors.toList());
        tagRepository.saveAll(tags1);
        //emotion 저장
        List<Tag> tags2 = postDto.getPostingCat().stream().map(tag -> new Tag(food, tag, "category")).collect(Collectors.toList());
        tagRepository.saveAll(tags2);
        //posting 저장 (foodId, userId, post 내용 저장)
        Food food2 = foodRepository.findByName(postDto.getPostingFoodName());
        Posting posting = new Posting(postDto, user, food2);
        postingRepository.save(posting);

    }
}





