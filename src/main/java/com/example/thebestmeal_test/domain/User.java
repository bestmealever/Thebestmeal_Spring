package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.UserStatusModifyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity // DB 테이블 역할을 합니다.
public class User extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String username;

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

    @OneToMany(mappedBy="user")
    private List<Posting> postings;

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

    public User(String username, String password, String email, UserRole role,Long kakaoId) {
        this.username = username;
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

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setRole(final UserRole role) {
        this.role = role;
    }

    public void setKakaoId(final Long kakaoId) {
        this.kakaoId = kakaoId;
    }

    public void setProfilePhoto(final String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setStatusMessage(final String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setPostings(final List<Posting> postings) {
        this.postings = postings;
    }

    public void setLikedFood(final LikedFood likedFood) {
        this.likedFood = likedFood;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public UserRole getRole() {
        return this.role;
    }

    public Long getKakaoId() {
        return this.kakaoId;
    }

    public String getProfilePhoto() {
        return this.profilePhoto;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public List<Posting> getPostings() {
        return this.postings;
    }

    public LikedFood getLikedFood() {
        return this.likedFood;
    }

    public User() {
    }
}
