package com.example.thebestmeal_test.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
    private boolean admin;
//    private boolean admin = false;
    private String adminToken;
//    private String adminToken = "";
}