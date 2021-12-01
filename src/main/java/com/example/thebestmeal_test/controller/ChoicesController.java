package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.dto.ChoiceDto;
import com.example.thebestmeal_test.repository.TagRepository;
import com.example.thebestmeal_test.service.ChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChoicesController {

    private final ChoiceService choiceService;

    @PostMapping("/recommend")
    public String choice(@RequestBody ChoiceDto choiceDto) {
        choiceService.toChoiceService(choiceDto);
        return "wow";
    }
}

