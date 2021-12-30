package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@ToString
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column
    private String tagName;

    @Column
    private String tagType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;

//    public Tag(PostDto postDto, Food food) {
//        this.tagName = postDto.getTag();
//        this.food = food;
//    }

    public Tag(Food food, String tagName, String tagType) {
        this.food = food;
        this.tagName = tagName;
        this.tagType = tagType;
    }

}