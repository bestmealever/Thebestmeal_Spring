package com.example.thebestmeal_test.Error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    /* 실제로 유저에게 보낼 응답 Format 입니다.
    일부러 500 에러 났을 때랑 형식을 맞췄습니다. status, code 값은 사실 없어도 됩니다.
    ErrorCode 를 받아서 ResponseEntity<ErrorResponse> 로 변환해줍니다. */
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getDetail())
                        .build()
                );
    }
}