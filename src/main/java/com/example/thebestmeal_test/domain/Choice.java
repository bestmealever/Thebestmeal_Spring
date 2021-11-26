package com.example.thebestmeal_test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@Entity
public class Choice extends Timestamped {

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Id
//    private Long id;

    public List<String> categoryWant;
    public List<String> emotionWant;
    public List<String> yesterdayEat;


}
