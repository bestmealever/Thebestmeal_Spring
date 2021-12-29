package com.example.thebestmeal_test.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    /* 전역으로 사용할 CustomException 입니다.
    RuntimeException 을 상속받아서 Unchecked Exception 으로 활용합니다.
    생성자로 ErrorCode 를 받습니다. */
    private final ErrorCode errorCode;
}