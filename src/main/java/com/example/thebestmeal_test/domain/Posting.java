package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Posting extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Identity로 바꿔야함.  기존 DB 랑 충돌하니 물리적 DB 지우고 올리기.
//    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY)
    private Long postingId;

    @Column(nullable = false)
    private String postingFoodName;

    @Column
    private String postingMemo;

    @Column(nullable = true)
    private String foodImgUrl;

    @Column(nullable = true)
    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;

    public Posting(PostDto postDto, User user, Food food) {
        this.postingMemo = postDto.getPostingMemo();
        this.postingFoodName = postDto.getPostingFoodName();
        this.foodImgUrl = foodImgUrl;
        this.user = user;
        this.food = food;
    }

}