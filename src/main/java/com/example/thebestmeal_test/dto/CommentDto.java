package com.example.thebestmeal_test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private long articleIdx;
    private String comment;
    private long userId;
}