package com.example.thebestmeal_test.dto;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.Recommended;
import com.example.thebestmeal_test.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MyPageDto {
    private User user;
    private List<Food> foods;
    private List<Recommended> recommendedList;
    private List<Posting> postings;

    public MyPageDto(User user, List<Food> foods, List<Recommended> recommendedList, List<Posting> postings) {
        this.user = user;
        this.foods = foods;
        this.recommendedList = recommendedList;
        this.postings = postings;
    }
}
