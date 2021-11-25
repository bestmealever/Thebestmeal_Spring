package com.example.thebestmeal_test.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posting extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postingId;

    @Column(nullable = false)
    private String postingFoodName;

    @Column
    private String postingMemo;

    @OneToOne
    @JoinColumn(nullable = false)
    private Food foodId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User userId;
}
