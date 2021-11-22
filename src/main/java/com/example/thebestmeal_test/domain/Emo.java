package com.example.thebestmeal_test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Emo {

    @Id
    private Long id;

    @Column
    private String emo;

}
