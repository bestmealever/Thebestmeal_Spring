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
    private Long idx;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false) // 사진 꼭 추가!
    private String imageUrl;

    @OneToMany(mappedBy = "food")
    private List<Tag> tags;

    @OneToOne
    private LikedFood likedFood;

    @OneToOne
    private Posting posting;

//    public Food(FoodRequestDto foodRequestDto, Long foodId) {
//        this.foodId = foodId;
//        this.foodName = getFoodName();
//        this.imageUrl = getImageUrl();
//        this.tags = getTags();
//        this.likedCount = getLikedCount();
//        this.postingId = getPostingId();
//    }
}
