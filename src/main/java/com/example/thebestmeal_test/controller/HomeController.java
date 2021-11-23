package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.security.UserDetailsImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
//    @GetMapping("/")
//    public String home(Model model) {
//        return "index";
//    }
//    @GetMapping("/")
//    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        model.addAttribute("username", userDetails.getUsername());
//        return "index";
//    }

    @GetMapping("/recommend")
    public String recommend() {
        return "recommend.html";
    }

//    @Secured("ROLE_ADMIN")
//    @GetMapping("/admin")
//    public String admin(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        model.addAttribute("username", userDetails.getUsername());
//        model.addAttribute("admin", true);
//        return "index";
//    }
}
