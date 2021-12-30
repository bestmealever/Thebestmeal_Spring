package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.domain.*;
import com.example.thebestmeal_test.dto.ArticleDto;
import com.example.thebestmeal_test.dto.UserDto;
import com.example.thebestmeal_test.repository.ArticleRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleServiceTest {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    User nowUser;

    @BeforeEach
    void beforeEach() throws IOException {
        String username = "testmokito";
        User user = new User(
                username, "testmokito1", "test@test.com", UserRole.USER
        );
        this.nowUser = user;
//        User user1 = userRepository.findByUsername("testmokito").orElseThrow(()->new NullPointerException("아이디 없네요"));
//        assertEquals("유저 생성 확인.", user.getId(), user1.getId());
        assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    @Order(1)
    @DisplayName("articleType vote 게시글이 잘 생성되는지 확인")
    void createArticleVote() throws IOException {

        //given
        ArticleDto articleDto = new ArticleDto(
                "이거 투표해주세요", "뭐먹을까요? 정말 고민됩니다", "닭도리탕","김치", ArticleType.vote
        );

        String url = "";
        Vote vote = new Vote(articleDto.getOption1Name(), articleDto.getOption2Name());
        Article article = new Article(articleDto.getTitle(), articleDto.getContent(), articleDto.getArticleType(),nowUser,vote,url);

       //when
        Article articleTest = articleRepository.findById(article.getId()).orElseThrow(
                ()->new NullPointerException("글 생성 안됨"));

        // then
        //assertEquals("글 생성 확인.", article.getId(), articleTest.getId());
        assertThat(article.getTitle()).isEqualTo(articleDto.getTitle());
    }


}