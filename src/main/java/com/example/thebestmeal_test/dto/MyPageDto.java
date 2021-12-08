package com.example.thebestmeal_test.dto;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MyPageDto {
    private User user;
    private List<Food> foods;

    public MyPageDto(User user, List<Food> foods) {
        this.user = user;
        this.foods = foods;
    }
}
