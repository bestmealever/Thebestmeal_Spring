package com.example.thebestmeal_test.dto;

import com.example.thebestmeal_test.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class PostDto {
    private String postingFoodName;
    private String Tag;
    private String foodImgUrl;
    private String postingMemo;
}
