package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.dto.SignupRequestDto;
import com.example.thebestmeal_test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//리팩토링 이렇게 해도 되나..?
//@RequiredArgsConstructor
//@Controller
//public class UserController {
//
//    private final UserService userService;
//
//}


}