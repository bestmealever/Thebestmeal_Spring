package com.example.thebestmeal_test.dto;

import com.example.thebestmeal_test.domain.ArticleType;
import com.example.thebestmeal_test.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
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

    public ArticleDto(String title, String content, String option1Name, String option2Name, ArticleType articleType) {
        this.title = title;
        this.content = content;
        this.option1Name = option1Name;
        this.option2Name = option2Name;
        this.articleType = articleType;
    }

    public ArticleDto(String title, String content, ArticleType articleType) {
        this.title = title;
        this.content = content;
        this.articleType = articleType;
    }

    // Slack 상태 메세지 전송을 위한 내부 클래스
    @Getter
    @Setter
    public static class Request {
        private String title;
        private String content;
        private MultipartFile image;
    }

    @Getter
    @Setter
    public static class Response {
        private Long idx;
        private String title;
        private String imageUrl;
    }
}
