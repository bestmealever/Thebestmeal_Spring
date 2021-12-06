package com.example.thebestmeal_test.kakao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoController {

    @GetMapping("/kakao")
    public String getPageKakao(){
        return "kakao.html";
    }
}
