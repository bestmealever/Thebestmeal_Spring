package com.example.thebestmeal_test.admin;


import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminDto {
    private List<Posting> postings;
    private List<Posting> postingAcceptedList;
    private List<Posting> postingDeclinedList;
    private List<Food> postingFoods;
    private List<Food> postingAcceptedFoods;
    private List<Food> postingDeclinedFoods;


    public AdminDto(List<Posting> postings, List<Posting> postingAcceptedList, List<Posting> postingDeclinedList, List<Food> postingFoods, List<Food> postingAcceptedFoods, List<Food> postingDeclinedFoods) {
        this.postings = postings;
        this.postingAcceptedList = postingAcceptedList;
        this.postingDeclinedList = postingDeclinedList;
        this.postingFoods = postingFoods;
        this.postingAcceptedFoods = postingAcceptedFoods;
        this.postingDeclinedFoods = postingDeclinedFoods;
    }

    //
//    public AdminDto(List<Food> foods,List<Posting> postings, List<Posting> postingsAccepted,  List<Posting> postingsDeclined) {
//        this.foods = foods;
//        this.postings = postings;
//        this.postingsAccepted = postingsAccepted;
//        this.postingsDeclined = postingsDeclined;
//    }

}
