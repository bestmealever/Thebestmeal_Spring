package com.example.thebestmeal_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoMapController {

    @GetMapping("/api/kakao")
    public String test() {
        return "kakao";
    }
}
