package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.dto.ChoiceDto;
import com.example.thebestmeal_test.service.ChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChoicesController {

    private final ChoiceService choiceService;

    @PostMapping("/recommend")
    public String choice(@RequestBody ChoiceDto choiceDto) {
        choiceService.getChoices(choiceDto);
        return "wow";
    }
}

