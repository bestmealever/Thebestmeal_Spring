package com.example.thebestmeal_test.dto;


import com.example.thebestmeal_test.repository.PostingRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostDto {

    private final PostingRepository postingRepository;

    private String postingFoodName;
    private String postingMemo;
}
