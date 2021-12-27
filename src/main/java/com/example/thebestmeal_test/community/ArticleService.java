package com.example.thebestmeal_test.community;

import com.example.thebestmeal_test.Error.CustomException;
import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.service.AwsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.thebestmeal_test.Error.ErrorCode.FOOD_NOT_FOUND;
import static com.example.thebestmeal_test.Error.ErrorCode.MEMBER_NOT_FOUND;
import static com.example.thebestmeal_test.community.ArticleType.*;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final FoodRepository foodRepository;
    private final ArticleTagRepository articleTagRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final VoteRepository voteRepository;
    private final CommentRepository commentRepository;
    private final AwsService awsService;

    @Transactional
    public Article postArticle(ArticleDto articleDto, UserDetailsImpl userDetails) throws IOException {
        String url = null;
        if(articleDto.getImage() != null) url = awsService.uploadCommunityImg(articleDto.getImage());
        ArticleType articleType = articleDto.getArticleType();
        Article savedArticle = new Article();
        String title = articleDto.getTitle();
        String content = articleDto.getContent();
        String x = articleDto.getX();
        String y = articleDto.getY();
        String placeName = articleDto.getPlaceName();
        String placeAddress = articleDto.getPlaceAddress();
        String placeUrl = articleDto.getPlaceUrl();
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        switch(articleType)
        {
            case recipe:
                String tagText= articleDto.getTags();
                Food food = foodRepository.findById(articleDto.getFoodidx()).orElseThrow(() -> new CustomException(FOOD_NOT_FOUND));
                Article article1 = new Article(title, content, articleType, food, user, url);
                savedArticle = articleRepository.save(article1);
                createTag(tagText,article1);
                break;
            case vote:
                String option1Name = articleDto.getOption1Name();
                String option2Name = articleDto.getOption2Name();
                Vote vote = new Vote(option1Name, option2Name);
                voteRepository.save(vote);
                Article article2 = new Article(title, content, articleType, user, vote, url);
                savedArticle = articleRepository.save(article2);
                break;
            case chat:
                Article article3 = new Article(title, content, articleType, user, url);
                savedArticle = articleRepository.save(article3);
                break;
            case place:
                Article article4 = new Article(title, content, articleType, user, x, y, placeName, placeAddress, placeUrl, url);
                savedArticle = articleRepository.save(article4);
                break;
        }
        return savedArticle;
    }

    public void createTag(String tagText, Article article) {
        List<String> tagList = Arrays.asList(tagText.split("\\s*,\\s*"));
        List<ArticleTag> tags= tagList.stream().map(tag -> new ArticleTag(article, tag)).collect(Collectors.toList());
        articleTagRepository.saveAll(tags);
    }

//    public ResponseEntity<?> relatedFood(Long foodid) {
//        Food food = foodRepository.findById(foodid).orElseThrow(()-> new NullPointerException("음식 없어요"));
//        if (food == null) {
//            //그냥 문자로 등록
//        } {
//            //food 정보 리턴 -_-
//        }
//    }

    public Page<Article> getArticles(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return articleRepository.findAll(pageable);
    }

    public Page<Article> getAllArticles(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return articleRepository.findAll(pageable);
    }

    public String postComment(CommentDto commentDto) {
        Article article = articleRepository.findById(commentDto.getArticleIdx()).orElseThrow(()-> new NullPointerException("해당 게시글이 지워졌습니다."));
        commentRepository.save(new Comment(article, commentDto.getComment()));
        return "비회원님이 댓글을 남기셨군요";
    }

    public String postComment(CommentDto commentDto, UserDetails userDetails) throws Exception {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new NullPointerException("없음"));
        Article article = articleRepository.findById(commentDto.getArticleIdx()).orElseThrow(()-> new NullPointerException("해당 게시글이 지워졌습니다."));
        commentRepository.save(new Comment(article, commentDto.getComment(),user));
        return "회원님이 댓글을 남기셨군요";
    }
}
