package com.example.thebestmeal_test.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column
    private Long kakaoId;

    @Column
    private String role;

    @Column
    private String profilePhoto;

    @Column
    private String statusMessage;

    @OneToMany(mappedBy = "User")
    private List<Posting> posting;
}
