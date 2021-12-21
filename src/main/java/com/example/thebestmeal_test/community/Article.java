package com.example.thebestmeal_test.community;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.domain.Timestamped;
import com.example.thebestmeal_test.domain.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
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

    //TODO : 태그 엔티티, 컬럼 넣기, 게시글의 HTML을 데이터베이스에 저장가능한지?
    @ManyToOne
    @JoinColumn
    private Food food;

    @ManyToOne
    @JoinColumn
    private User user;

    @OneToMany
    private Set<Tag> tags;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArticleType articleType;

    public Article(String title, String content, ArticleType articleType, Food food, User user) {
        this.title = title;
        this.content = content;
        this.articleType = articleType;
        this.food = food;
        this.user = user;
    }
}