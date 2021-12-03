package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @OneToMany(mappedBy = "food")
    private List<Tag> tags;

    @OneToMany(mappedBy = "food")
    private List<LikedFood> likedFood;

    public Food(PostDto postDto) {
        this.name = postDto.getPostingFoodName();
        this.imageUrl = postDto.getFoodImgUrl();
    }

    public Food(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
