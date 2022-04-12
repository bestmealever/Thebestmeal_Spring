package com.example.thebestmeal_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
public class StatusAlarmDto {

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
        private long id;
        private String title;
        private String imageUrl;
    }
}
