package com.example.thebestmeal_test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class LikedFood extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likedFoodId;

    @Column(nullable = false)
    private String likedFoodName;

    private Long likedCount;

    @OneToOne
    @JoinColumn
    private Food foodId;
}
