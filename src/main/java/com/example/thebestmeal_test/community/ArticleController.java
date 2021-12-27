package com.example.thebestmeal_test.community;

import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.repository.TagRepository;
import com.example.thebestmeal_test.responseEntity.BasicResponse;
import com.example.thebestmeal_test.responseEntity.CommonResponse;
import com.example.thebestmeal_test.responseEntity.ErrorResponse;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;

    @PostMapping("/article")
    public ResponseEntity<Message> postArticle(ArticleDto articleDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        Article article = articleService.postArticle(articleDto,userDetails);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        ResponseEntity<Message> response = new ResponseEntity<>(message, headers, HttpStatus.OK);
        if (userDetails == null) {
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("로그인 후 이용해주세요");
        }
        if(article.getContent() == null) {
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("글 내용을 작성해주세요.");
        } else {
            message.setStatus(StatusEnum.OK);
            message.setMessage("글 작성 성공");
            message.setData(article);
        }
        return response;
    }

//    @GetMapping("/articles")
//    private List<Article> getArticle() {
//        return articleRepository.findAll();
//    }

    @PostMapping("/article/comment")
    public String postComment(@RequestBody CommentDto commentDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        if (userDetails == null) {
            return articleService.postComment(commentDto);
        }
        return articleService.postComment(commentDto,userDetails);
    }

    @DeleteMapping("/article/{id}")
    public String deleteArticle(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
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

    @DeleteMapping("/article/comment/{id}")
    public String deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        Long userId = userDetails.getUser().getId();
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NullPointerException("없음"));
        Long commentUserId = comment.getUser().getId();
        if (Objects.equals(commentUserId, userId)) {
            commentRepository.delete(comment);
            return "삭제 완료!";
        }
        return "자신이 작성한 댓글만 삭제 가능합니다.";
    }

    @GetMapping("/articles/{id}")
    public Article getArticles(@PathVariable Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new NullPointerException("없슈"));
    }
}
