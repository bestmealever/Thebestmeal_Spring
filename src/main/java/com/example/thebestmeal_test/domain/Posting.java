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


    //    Identity로 바꿔야함.  기존 DB 랑 충돌하니 물리적 DB 지우고 올리기.
    //    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String postingFoodName;

    @Column
    private String postingMemo;

//    @Column(nullable = true)
//    private String foodImgUrl;

//    @Column(nullable = true)
//    private String status;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PostingStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @JsonIgnore
    @OneToOne
    @JoinColumn(nullable = false)
    private Food food;


    //PostingStatus 추가 foodImgUrl 제거 (이미 foodDB에 있는 칼럼)
    public Posting(PostDto postDto, User user, Food food, PostingStatus status) {
        this.postingMemo = postDto.getPostingMemo();
        this.postingFoodName = postDto.getPostingFoodName();
//        this.foodImgUrl = foodImgUrl;
        this.user = user;
        this.food = food;
        this.status = status;
    }

    public void update(PostingStatus status) {
        this.status = status;
    }

}