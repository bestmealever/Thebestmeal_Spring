package com.example.thebestmeal_test.dto;

import com.example.thebestmeal_test.domain.ArticleType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ArticleDto {
    private String title;
    private String content;
    private String tags;
    private Long foodidx;
    private MultipartFile image;
    private String place;
    private String option1Name;
    private String option2Name;
    private ArticleType articleType;
    private String x;
    private String y;
    private String placeName;
    private String placeAddress;
    private String placeUrl;
}
