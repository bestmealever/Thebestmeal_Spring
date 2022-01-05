package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy= "food")
    private List<Recommended> recommendeds;

    @JsonIgnore
    @OneToOne(mappedBy="food")
    @JoinColumn(nullable = false)
    private Posting posting;

    //Specifies that the property or field is not persistent. Transient - 칼럼으로 구성해서 관리할 필요가 없을 때.
    @Transient
    private boolean liked = false;

    public Food(PostDto postDto, String imageUrl) {
        this.name = postDto.getPostingFoodName();
        this.imageUrl = imageUrl;
    }

    public Food(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Food(Food food, Posting posting) {
        this.name = food.getName();
        this.imageUrl = food.getImageUrl();
        this.posting = posting;
    }

    public void update(int cnt) {
        this.cnt += cnt;
    }
}
