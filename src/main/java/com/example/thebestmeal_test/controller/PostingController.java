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
                () -> new NullPointerException("그런 사람 없는데요?"));

        //1

//        //2
//        Food namecheck = foodRepository.findByName(postDto.getPostingFoodName()).orElseThrow(() -> new NullPointerException)

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

//    @GetMapping("/api/dupcheck")
//    public static boolean isUnique(@RequestBody PostDto postDto)
//    postDto.getPostingFoodName())
//                if (foodRepository.findByName(postDto.getPostingFoodName())!= null) {
//        return ("이미 존재하는 음식입니다!");
//    }

    //post 에서 foodname check 는 어떤 순서로 부르지?
    //Boolean 타입으로 받아야 하나? 어떻게 받아서 페이지에 어떻게 전해주지?
    //name check 를 안넣고,
//    @GetMapping("/foodnamecheck")
//    //@requestParam (required = false) (searchTag가 들어가있지 않아도 허용해줌)
//    public Boolean nameCheck(@RequestParam(required = false) String postingFoodName){
//        if (foodRepository.findAllByName(postingFoodName))!= null {
//            exception("이미 존재하는 음식입니다!");
//        }
//        else {
//           // 어떻게넘기지..?
//        }
//    }
    // POST 요청.. isboolean체크. (1) 컨트롤러에서 exists =


}





