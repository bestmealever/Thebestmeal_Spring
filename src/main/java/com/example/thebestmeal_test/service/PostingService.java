package com.example.thebestmeal_test.service;
import com.example.thebestmeal_test.domain.*;
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

        // 음식명 중복 체크를 거쳐 foodRepo에 저장이 된 food 의 데이터만 가져옴 (null값을 걸러준다)
        // food는 이미 음식객체로 위에서 저장이 되었음. food2는 foodID 와 함께 저장되어서, 나중에 참조가 가능해짐. 위와 값은 같지만, 구분해주기 위해서 food2라고 표시.
        Food food2 = foodRepository.findByName(postDto.getPostingFoodName()).get();
        //status 에 to be reviewed가 자동으로 기본값.
        PostingStatus status = PostingStatus.ToBeReviewed;
        //posting 저장
        Posting posting = new Posting(postDto, user, food2, status);
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
