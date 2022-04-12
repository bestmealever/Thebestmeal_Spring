package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.ArticleDto;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Article extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String image;

    @Column
    private String x;

    @Column
    private String y;

    @Column
    private String placeName;

    @Column
    private String placeAddress;

    @Column
    private String placeUrl;

    @ManyToOne
    @JoinColumn
    private Food food;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany
    private Set<ArticleTag> tags;

    @OneToOne
    private Vote vote;

    @OneToMany(mappedBy = "article", cascade=CascadeType.ALL)
    private List<Comment> comments;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArticleType articleType;

    public Article(String title, String content, ArticleType articleType, Food food, User user, String url) {
        this.title = title;
        this.content = content;
        this.articleType = articleType;
        this.food = food;
        this.user = user;
        this.image = url;
    }

    public Article(String title, String content, ArticleType articleType, User user, Vote vote, String url) {
        this.title = title;
        this.content = content;
        this.articleType = articleType;
        this.user = user;
        this.vote = vote;
        this.image = url;
    }

    public Article(String title, String content, ArticleType articleType, User user, String url) {
        this.title = title;
        this.content = content;
        this.articleType = articleType;
        this.user = user;
        this.image = url;
    }

    public Article(String title, String content, ArticleType articleType, User user, String x, String y,String placeName,String placeAddress, String placeUrl, String url) {
        this.title = title;
        this.content = content;
        this.articleType = articleType;
        this.user = user;
        this.x = x;
        this.y = y;
        this.placeName = placeName;
        this.placeUrl = placeUrl;
        this.placeAddress = placeAddress;
        this.image = url;
    }

    // Slack 상태 메세지 관련 생성자
    public Article(ArticleDto.Request request, String url) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.image = url;
    }

}