package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.dto.ChoiceDto;
import com.example.thebestmeal_test.service.ChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChoicesController {

    private final ChoiceService choiceService;

    @PostMapping("/recommend")
    public String choice(@RequestBody ChoiceDto choiceDto) {
//        choiceService.set어쩌고저쩌고(choiceDto)
        return "wow";
    }
}

