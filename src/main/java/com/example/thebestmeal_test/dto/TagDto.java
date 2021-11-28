package com.example.thebestmeal_test.dto;


import com.example.thebestmeal_test.repository.TagRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TagDto {

    private final TagRepository tagRepository;

    private Long tagId;
    private String tagName;
}
