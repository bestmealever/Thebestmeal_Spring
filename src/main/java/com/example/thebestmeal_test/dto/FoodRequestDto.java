package com.example.thebestmeal_test.dto;


import com.example.thebestmeal_test.repository.FoodRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FoodRequestDto {

    private final FoodRepository foodRepository;

    private String foodName;
    private String imageUrl;
}
