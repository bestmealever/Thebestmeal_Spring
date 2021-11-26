package com.example.thebestmeal_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Tag {
    // 별도의 코드값을 지정할까 해서 GenerationType을 지정하지 않았습니다. (ex. category - 10000001, emotion - 50000001)
    // 만약 별도의 코드값을 지정하지 않는다면, GenerationType 지정 후 자동 증가로 설정해도 될 것 같습니다.
    @Id
    private Long tagId;

    @Column(nullable = false)
    private String tagName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;
}
