package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagId;

    @Column(nullable = false)
    private String tagName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Food foodId;

    public Tag(PostDto postDto, Food food) {
        this.tagName = postDto.getTag();
        this.foodId = food;
    }
}