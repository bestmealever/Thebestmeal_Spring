package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.domain.*;
import com.example.thebestmeal_test.dto.ArticleDto;
import com.example.thebestmeal_test.dto.CommentDto;
import com.example.thebestmeal_test.dto.SignupRequestDto;
import com.example.thebestmeal_test.repository.ArticleRepository;
import com.example.thebestmeal_test.repository.CommentRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class ArticleServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    User nowUser;
    UserDetailsImpl userDetails;

    String title = "게시글 제목입니다";
    String content = "게시글 내용입니다";
    String option1Name = "투표 후보 1";
    String option2Name = "투표 후보 2";
    String url = "image.jpg";


    @BeforeEach
    void beforeEach() throws Exception {
        String username = "testmokito";
        SignupRequestDto signupRequest = new SignupRequestDto(username, "testmokito1", "test@test.com",false,"");
        userService.registerUser(signupRequest);
        User user = userRepository.findByUsername(username).orElseThrow(()->new NullPointerException("아이디 없네요"));
        this.nowUser = user;
        this.userDetails = new UserDetailsImpl(user);
    }

    @Test
    @Order(1)
    @DisplayName("vote 게시글이 잘 생성되는지 확인")
    void createArticleVote() throws Exception {

        //given
        ArticleDto articleDto = new ArticleDto(
                title, content, option1Name, option2Name, ArticleType.vote
        );

        Article article = articleService.postArticle(articleDto, userDetails);

       //when
        Article articleTest = articleRepository.findById(article.getId()).orElseThrow(
                ()->new NullPointerException("글 생성 안됨"));

        // then
        assertEquals("글 생성 확인.", article.getId(), articleTest.getId());
        assertEquals("글 생성 확인.", article.getTitle(), title);
    }

    @Test
    @Order(2)
    @DisplayName("잡담 게시글이 잘 생성되는지 확인")
    void createArticleChat() throws Exception {

        ArticleType articleType = ArticleType.chat;

        //given
        ArticleDto articleDto = new ArticleDto(
                title, content, articleType
        );

        Article article = articleService.postArticle(articleDto, userDetails);

        //when
        Article articleTest = articleRepository.findById(article.getId()).orElseThrow(
                ()->new NullPointerException("글 생성 안됨"));

        // then
        assertEquals("글 생성 확인.", article.getId(), articleTest.getId());
        assertEquals("글 생성 확인.", article.getTitle(), title);
    }

    @Test
    @Order(3)
    @DisplayName("게시글에 댓글달기")
    void createComment() throws Exception {
        //given
        ArticleType articleType = ArticleType.chat;
        ArticleDto articleDto = new ArticleDto(title, content, articleType);
        Article article = articleService.postArticle(articleDto, userDetails);
        //댓글을 달 새로운 유저 생성
        String username = "testmokito2";
        SignupRequestDto signupRequest = new SignupRequestDto(username, "testmokito1", "test@test.com",false,"");
        userService.registerUser(signupRequest);
        User commentUser = userRepository.findByUsername(username).orElseThrow(()->new NullPointerException("아이디 없네요"));
        UserDetailsImpl userDetailsImpl2 = new UserDetailsImpl(commentUser);

        String comment = "댓글을 달았습니다";

        CommentDto commentDto = new CommentDto(
                article.getId(),comment,commentUser.getId()
        );
        articleService.postComment(commentDto, userDetailsImpl2);

        List<Comment> commentList = commentRepository.findAll();
        List<Comment> savedComment = commentList.stream().filter(t -> t.getComment().equals(comment)).collect(Collectors.toList());

        // then
        for (Comment commentsss : savedComment) {
            Long id = commentsss.getArticle().getId();
            System.out.println(commentsss.getComment());
            assertEquals("코멘트 생성 확인.", article.getId(), id);
        }
    }

    @Test
    @Order(4)
    @DisplayName("본인이 작성하지 않은 게시글 삭제")
    void deleteArticle() throws Exception {
        //given
        ArticleType articleType = ArticleType.chat;
        ArticleDto articleDto = new ArticleDto(title, content, articleType);
        Article article = articleService.postArticle(articleDto, userDetails);

        //게시글 삭제할 새로운 유저 생성
        String username = "testmokito2";
        SignupRequestDto signupRequest = new SignupRequestDto(username, "testmokito1", "test@test.com",false,"");
        userService.registerUser(signupRequest);
        User hacker = userRepository.findByUsername(username).orElseThrow(()->new NullPointerException("아이디 없네요"));
        UserDetailsImpl userDetailsImpl2 = new UserDetailsImpl(hacker);

        // then
        String a = articleService.deleteArticle(article.getId(), userDetailsImpl2);
        assertThat(a).contains("자신이 작성한 글만 삭제");

    }


}