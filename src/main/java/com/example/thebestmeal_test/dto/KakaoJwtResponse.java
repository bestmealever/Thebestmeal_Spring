package com.example.thebestmeal_test.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter

public class KakaoJwtResponse {
    private final String token;
    private final String username;
    private final String nickname;

}
