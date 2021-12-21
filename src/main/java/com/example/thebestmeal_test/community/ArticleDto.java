package com.example.thebestmeal_test.community;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class ArticleDto {
    private String title;
    private String content;
    private String tags;
    private Long foodidx;
    private String place;
    private String vote;
    private ArticleType articleType;
}
