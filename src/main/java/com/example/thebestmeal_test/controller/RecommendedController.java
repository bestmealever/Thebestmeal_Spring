package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RecommendedController {

    private final UserRepository userRepository;

    @GetMapping("/recommended")
    public Optional<User> getMyinfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userRepository.findByUsername(userDetails.getUsername()));
        return userRepository.findByUsername(userDetails.getUsername());
    }

//    @GetMapping("/recommended")
//    public String test() {
//        return "wow";
//    }
}
