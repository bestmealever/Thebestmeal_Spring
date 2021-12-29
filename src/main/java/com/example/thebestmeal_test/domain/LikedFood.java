package com.example.thebestmeal_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class LikedFood extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Food food;

    public LikedFood(Food food, User user) {
        this.user = user;
        this.food = food;
    }
}
