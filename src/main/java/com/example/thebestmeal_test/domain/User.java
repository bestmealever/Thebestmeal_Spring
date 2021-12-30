package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.UserDto;
import com.example.thebestmeal_test.dto.UserStatusModifyDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity // DB 테이블 역할을 합니다.
public class User extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private Long kakaoId;

    @Column
    private String profilePhoto;

    @Column
    private String statusMessage;

    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Posting> postings;

    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Recommended> recommendeds;

    @OneToOne
    private LikedFood likedFood;

    public User(String username, String password, String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.kakaoId = 0L;
        this.statusMessage = "한마디해주세요!";
        this.profilePhoto = "/images/profile_pic.jpg";
    }

    public User(String nickname, String username, String password, String email, UserRole role, Long kakaoId) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.kakaoId = kakaoId;
        this.statusMessage = "한마디해주세요!";
        this.profilePhoto = "/images/profile_pic.jpg";
    }

    public void update(String uploadImageUrl) {
        this.profilePhoto = uploadImageUrl;
    }

    public void update(UserStatusModifyDto statusModifyDto) {
        this.statusMessage = statusModifyDto.getStatusMessage();
    }

}
