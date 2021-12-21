package com.example.thebestmeal_test.community;

import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ArticleController {
    private final ArticleService articleService;
    @PostMapping("/article")
    public String postArticle(@RequestBody ArticleDto articleDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return articleService.postArticle(articleDto,userDetails);
    }
}
