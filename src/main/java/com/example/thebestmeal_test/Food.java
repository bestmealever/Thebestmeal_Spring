package com.example.thebestmeal_test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "foodid")
    private Long id;
    @Column(nullable = false)
    private String foodname;
    @Column(nullable = false)
    private String cat1;
    @Column
    private String cat2;
    @Column
    private String cat3;
    @Column(nullable = false)
    private String emo1;
    @Column
    private String emo2;
    @Column
    private String emo3;
    @Column
    private String emo4;
    @Column
    private String emo5;
    @Column(nullable = false)
    private String imageurl;
}
