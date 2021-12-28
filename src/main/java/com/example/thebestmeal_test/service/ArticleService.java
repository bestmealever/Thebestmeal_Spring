package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.Error.CustomException;
import com.example.thebestmeal_test.domain.ArticleType;
import com.example.thebestmeal_test.domain.*;
import com.example.thebestmeal_test.dto.ArticleDto;
import com.example.thebestmeal_test.dto.CommentDto;
import com.example.thebestmeal_test.dto.VoteDto;
import com.example.thebestmeal_test.repository.*;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.thebestmeal_test.Error.ErrorCode.FOOD_NOT_FOUND;
import static com.example.thebestmeal_test.Error.ErrorCode.MEMBER_NOT_FOUND;

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

    public String deleteArticle(Long id, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        Article article = articleRepository.findById(id).orElseThrow(() -> new NullPointerException("없음"));
        Long articleId = article.getUser().getId();
        if (Objects.equals(articleId, userId)) {
            articleRepository.delete(article);
            return "삭제 완료!";
        } else if (!Objects.equals(articleId, userId)) {
            return "자신이 작성한 글만 삭제 가능합니다.";
        }
        return "게시글을 삭제할 수 없습니다.";
    }

    public Page<Article> getArticles(int page, int size, String sortBy, boolean isAsc) {
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

    public String deleteComment(Long id, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NullPointerException("없음"));
        Long commentUserId = comment.getUser().getId();
        if (Objects.equals(commentUserId, userId)) {
            commentRepository.delete(comment);
            return "삭제 완료!";
        }
        return "자신이 작성한 댓글만 삭제 가능합니다.";
    }

    public void vote(VoteDto voteDto) {
        Long voteId = voteDto.getId();
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new NullPointerException("없음"));
        if (Objects.equals(voteDto.getOptionType(), "option1")) {
            vote.updateOption1(+1);
        } else {
            vote.updateOption2(+1);
        }
        voteRepository.save(vote);
    }
}
