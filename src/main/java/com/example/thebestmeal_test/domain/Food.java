package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Food extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "tag")
    private List<Tag> tags;

    public Food(PostDto postDto) {
        this.name = postDto.getPostingFoodName();
        this.imageUrl = postDto.getFoodImgUrl();
    }
}
