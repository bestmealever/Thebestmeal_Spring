package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Food extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long foodId;

    @Column
    private String foodName;

    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "Food")
    private List<Tag> tags = new ArrayList<>();

    @OneToOne(mappedBy = "Food")
    private LikedFood likedCount;

    @OneToOne(mappedBy = "Food")
    private Posting postingId;

    public Food(FoodRequestDto foodRequestDto, Long foodId) {
        this.foodId = foodId;
        this.foodName = getFoodName();
        this.imageUrl = getImageUrl();
        this.tags = getTags();
        this.likedCount = getLikedCount();
        this.postingId = getPostingId();
    }
}
