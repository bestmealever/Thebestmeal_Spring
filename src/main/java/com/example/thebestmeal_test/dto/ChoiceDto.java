package com.example.thebestmeal_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChoiceDto {
    public List<String> categoryWant;
    public List<String> yesterdayEat;
    public List<String> emotionWant;
}
