package com.example.thebestmeal_test.dto;

import com.example.thebestmeal_test.domain.UserRole;
import com.example.thebestmeal_test.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRequestDto {

    private final UserRepository userRepository;

    private String userName;
    private String password;
    private String email;
    private String kakaoId;
    private UserRole role;
    private String profilePhoto;
    private String statusMessage;

}
