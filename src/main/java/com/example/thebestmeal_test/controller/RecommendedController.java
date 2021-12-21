package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.service.RecommendedService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RecommendedController {

    private final RecommendedService recommendedService;

    @GetMapping("/recommended")
    public void getUserInfoAndFoodName(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("foodName") String foodName
    ) {
        if (userDetails != null) {
            recommendedService.saveRecommendedInfo(userDetails, foodName);
        }
    }
}
