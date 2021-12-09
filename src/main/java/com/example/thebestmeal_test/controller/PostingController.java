package com.example.thebestmeal_test.controller;

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
        return postingService.toPostFoodService(postDto, userDetails);
    }

    @PostMapping({"/foodcheck"})
    public Boolean foodCheck(@RequestBody FoodCheckDto foodCheckDto) {
        return postingService.foodDupCheck(foodCheckDto);
    }
}




