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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
    private final AwsService awsService;

    @Transactional
    @PostMapping("/post")
        public void postFood(PostDto postDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("그런 사람 없는데요?"));

//       food 저장
        String imageUrl = awsService.uploadFoodImg(postDto.getImageUrl());
        Food food = new Food(postDto, imageUrl);
        foodRepository.save(food);
        //category 저장
//        List<String> items1 = Arrays.asList(postDto.getPostingTag());
        List<Tag> tags1 = postDto.getPostingEmo().stream().map(tag -> new Tag(food, tag, "emotion")).collect(Collectors.toList());
        tagRepository.saveAll(tags1);
        //emotion 저장
        List<Tag> tags2 = postDto.getPostingCat().stream().map(tag -> new Tag(food, tag, "category")).collect(Collectors.toList());
        tagRepository.saveAll(tags2);
        //posting 저장 (foodId, userId, post 내용 저장)
        //.get() -> 그냥 Food 클래스의.. null check(Optional 객체로 한번 감싸짐) 이렇게 쓰면
        //Optional null이 get()
        Food food2 = foodRepository.findByName(postDto.getPostingFoodName()).get();
//        Food food2 = foodRepository.findByName(postDto.getPostingFoodName()).get();
        Posting posting = new Posting(postDto, user, food2);
        postingRepository.save(posting);

    }

    @PostMapping("/foodcheck")
    public Boolean foodCheck(@RequestBody FoodCheckDto foodCheckDto) {
        String postingFoodName = foodCheckDto.getPostingFoodName();
        //findByPostingFoodName 이 아니어도 될까?
        Optional<Food> found = foodRepository.findByName(postingFoodName);
        Boolean response = found.isPresent();
        System.out.println(response);
        return response;
    }

    //postingclass 와 FoodClass에 동시 저장.
//    @PostMapping("/foodimgupload")
//    public String foodupload(@RequestParam("foodimages") MultipartFile multipartFile, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
//        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
//                () -> new NullPointerException("그런 사람 없는데요?"));
//        Posting posting =
//                awsService.foodupload(multipartFile, "food_pic", posting);
//        return "사진 업로드 성공!";
//    }
}

