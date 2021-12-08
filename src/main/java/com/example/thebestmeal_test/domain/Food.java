package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
//   private String foodImgUrl;

    @OneToMany(mappedBy = "food")
    private Set<Tag> tags;

    @OneToMany(mappedBy = "food")
    private Set<LikedFood> likedFood;

    @OneToMany(mappedBy="food")
    private List<Recommended> recommendeds;

    public Food(PostDto postDto, String imageUrl) {
        this.name = postDto.getPostingFoodName();
        this.imageUrl = imageUrl;
    }

    public Food(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
