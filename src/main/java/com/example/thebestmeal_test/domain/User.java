package com.example.thebestmeal_test.domain;


import com.example.thebestmeal_test.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Column // 카카오 회원가입을 하지 않은 사람들에게는 Null 값일 수 있어서, Nullable을 별도로 지정하지 않았습니다.
    private Long kakaoId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column
    private String profilePhoto;

    @Column
    private String statusMessage;

    @OneToMany(mappedBy = "user")
    private List<Posting> postings;

    public User(UserRequestDto userRequestDto, String userName, String password, String email, Long kakaoId, UserRole role, String profilePhoto, String statusMessage, Posting postings) {
        this.userName = userRequestDto.getUserName();
        this.password = userRequestDto.getPassword();
        this.email = userRequestDto.getEmail();
        this.kakaoId = userRequestDto.getKakaoId();
        this.role = userRequestDto.getRole();
        this.profilePhoto = userRequestDto.getProfilePhoto();
        this.statusMessage = userRequestDto.getStatusMessage();
        this.postings =
    }
}
