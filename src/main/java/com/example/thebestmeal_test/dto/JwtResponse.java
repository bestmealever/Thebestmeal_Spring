package com.example.thebestmeal_test.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JwtResponse{

    private final String token;
    private final String username;
    private final String nickname;
}