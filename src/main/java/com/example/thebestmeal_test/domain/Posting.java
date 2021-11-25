package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;

    public Posting(PostDto postDto, User user, Food food) {
        this.postingMemo = postDto.getPostingMemo();
        this.postingFoodName = postDto.getPostingFoodName();
        this.user = user;
        this.food = food;
    }

    public Posting(PostDto postDto) {
        this.postingMemo = postDto.getPostingMemo();
        this.postingFoodName = postDto.getPostingFoodName();
    }
}