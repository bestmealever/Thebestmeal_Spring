package com.example.thebestmeal_test.service;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostingService {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final TagRepository tagRepository;
    private final PostingRepository postingRepository;
    private final AwsService awsService;

    public String toPostFoodService(PostDto postDto, UserDetailsImpl userDetails) throws IOException {

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new NullPointerException("그런 사람 없는데요?"));
        // FoodImg (파일) foodImgUrl(스트링) 구분
        String foodImgUrl = awsService.uploadFoodImg(postDto.getFoodImg());
        // url과 함께 food 저장.
        Food food = new Food(postDto, foodImgUrl);
        foodRepository.save(food);

        // tag - emotion 저장
        List<Tag> tags1 = postDto.getPostingEmo().stream().map((tag) -> new Tag(food, tag, "emotion")).collect(Collectors.toList());
        tagRepository.saveAll(tags1);

        // tag - category 저장
        List<Tag> tags2 = postDto.getPostingCat().stream().map((tag) -> new Tag(food, tag, "category")).collect(Collectors.toList());
        tagRepository.saveAll(tags2);

        // 음식명 중복 체크
        Food food2 = foodRepository.findByName(postDto.getPostingFoodName()).get();
        Posting posting = new Posting(postDto, user, food2);
        postingRepository.save(posting);

        return foodImgUrl;
    }

    public Boolean foodDupCheck(FoodCheckDto foodCheckDto) {
        String postingFoodName = foodCheckDto.getPostingFoodName();
        Optional<Food> found = foodRepository.findByName(postingFoodName);
        Boolean response = found.isPresent();
        System.out.println(response);
        return response;
    }
}

