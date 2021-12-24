package com.example.thebestmeal_test.admin;


import com.example.thebestmeal_test.domain.Posting;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminDto {
    private List<Posting> postings;
    private List<Posting> postingsAccepted;
//
    public AdminDto(List<Posting> postings, List<Posting> postingsAccepted) {
        this.postings = postings;
        this.postingsAccepted = postingsAccepted;
    }
}
