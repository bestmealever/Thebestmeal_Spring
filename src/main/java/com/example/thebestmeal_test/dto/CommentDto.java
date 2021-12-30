package com.example.thebestmeal_test.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {
    private long articleIdx;
    private String comment;
    private long userId;

}