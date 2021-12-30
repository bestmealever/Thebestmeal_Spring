package com.example.thebestmeal_test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ArticleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tagName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,CascadeType.REMOVE})
    private Article article;

    public ArticleTag(Article article, String tag) {
        this.article = article;
        this.tagName = tag;
    }
}
