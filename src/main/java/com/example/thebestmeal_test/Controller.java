package com.example.thebestmeal_test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/api/recommend")
    public void foodRecommend(@RequestBody RecomendRequestDto recomendRequestDto) {
    }
}
