package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.dto.ArticleDto;
import com.example.thebestmeal_test.service.ArticleService;
import com.example.thebestmeal_test.dto.CommentDto;
import com.example.thebestmeal_test.dto.VoteDto;
import com.example.thebestmeal_test.domain.*;
import com.example.thebestmeal_test.repository.ArticleRepository;
import com.example.thebestmeal_test.repository.CommentRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import com.example.thebestmeal_test.repository.VoteRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    private final VoteRepository voteRepository;

    @PostMapping("/article")
    public ResponseEntity<Message> postArticle(ArticleDto articleDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        Article article = articleService.postArticle(articleDto,userDetails);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        ResponseEntity<Message> response = new ResponseEntity<>(message, headers, HttpStatus.OK);
        if (userDetails == null) {
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("????????? ??? ??????????????????");
        }
        if(article.getContent() == null) {
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setMessage("??? ????????? ??????????????????.");
        } else {
            message.setStatus(StatusEnum.OK);
            message.setMessage("??? ?????? ??????");
            message.setData(article);
        }
        return response;
    }


    @PostMapping("/article/comment")
    public String postComment(@RequestBody CommentDto commentDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        if (userDetails == null) {
            return articleService.postComment(commentDto);
        }
        return articleService.postComment(commentDto,userDetails);
    }

    @PostMapping("/article/{id}")
    public String deleteArticle(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        return articleService.deleteArticle(id, userDetails);
    }

    @PostMapping("/article/comment/{id}")
    public String deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        return articleService.deleteComment(id, userDetails);
    }

    @GetMapping("/articles/{id}")
    public Article getArticles(@PathVariable Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new NullPointerException("??????"));
    }

    @GetMapping("/articles")
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @PostMapping("/vote")
    public void vote(@RequestBody VoteDto voteDto) {
        articleService.vote(voteDto);
    }
}
