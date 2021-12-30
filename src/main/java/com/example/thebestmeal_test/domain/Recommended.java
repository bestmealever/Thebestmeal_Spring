package com.example.thebestmeal_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Recommended extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Food food;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user;

    public Recommended(Food food, User user) {
        this.user = user;
        this.food = food;
    }
}
