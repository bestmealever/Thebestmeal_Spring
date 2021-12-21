package com.example.thebestmeal_test.community;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final FoodRepository foodRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public String postArticle(ArticleDto articleDto, UserDetailsImpl userDetails) {
        String title = articleDto.getTitle();
        String content = articleDto.getContent();
        ArticleType articleType = articleDto.getArticleType();
        String tagText= articleDto.getTags();
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(()->new NullPointerException("그런 사람 없음"));
        Food food = foodRepository.findById(articleDto.getFoodidx()).orElseThrow(() -> new NullPointerException("음식 찾기 못함"));
        createTag(tagText,food);
        new Article(title, content, articleType, food, user);
        return "등록 완료여";
    }

    public void createTag(String tagText, Food food) {
        List<String> tagList = Arrays.asList(tagText.split("\\s*,\\s*"));
        List<Tag> tags= tagList.stream().map(tag -> new Tag(food, tag,"article")).collect(Collectors.toList());
        tagRepository.saveAll(tags);
    }
}
