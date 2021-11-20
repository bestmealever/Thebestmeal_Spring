package com.example.thebestmeal_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ChoiceDto {
    public List<String> categoryWant;
    public List<String> emotionWant;
    public List<String> yesterdayEat;

    @Override
    public String toString() {
        return "ChoiceDto{" +
                "categoryWant=" + categoryWant +
                ", emotionWant=" + emotionWant +
                ", yesterdayEat=" + yesterdayEat +
                '}';
    }
}
