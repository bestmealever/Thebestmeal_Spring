package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

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

    @Column
    @ColumnDefault("0")
    private int cnt;

    @OneToMany(mappedBy = "food")
    private Set<Tag> tags;

    @OneToMany(mappedBy = "food")
    private Set<LikedFood> likedFood;

//    public Food(PostDto postDto) {
//        this.name = postDto.getPostingFoodName();
//        this.imageUrl = postDto.getFoodImgUrl();
//    }

//    public Food(String name, String imageUrl) {
//        this.name = name;
//        this.imageUrl = imageUrl;
//    }

    public Food(PostDto postDto, String foodImgUrl) {
        this.name = postDto.getPostingFoodName();
        this.imageUrl = foodImgUrl;
    }

    public void update(int cnt) {
        this.cnt += cnt;
    }
}
