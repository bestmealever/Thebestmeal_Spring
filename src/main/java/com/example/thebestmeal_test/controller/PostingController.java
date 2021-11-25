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
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostingController {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final TagRepository tagRepository;

    @PostMapping("/post")
    public void postFood(@RequestBody PostDto postDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("그런 사람 없는데요?")
        );
        Food food = new Food(postDto);
        foodRepository.save(food);
        Food food2 = foodRepository.findByName(postDto.getPostingFoodName());
        Tag tag = new Tag(postDto, food2);
        tagRepository.save(tag);
        Posting posting = new Posting(postDto, user, food2);
        postingRepository.save(posting);
    }
}
