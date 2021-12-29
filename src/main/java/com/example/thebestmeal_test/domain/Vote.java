package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.domain.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Vote extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long voteId;

    @Column(nullable = false)
    private String option1Name;

    @Column
    @ColumnDefault("0")
    private int option1;

    @Column(nullable = false)
    private String option2Name;

    @Column
    @ColumnDefault("0")
    private int option2;

    public Vote(String option1Name, String option2Name) {
        this.option1Name = option1Name;
        this.option2Name = option2Name;
    }

    public void updateOption1(int cnt) {
        this.option1 += cnt;
    }

    public void updateOption2(int cnt) {
        this.option2 += cnt;
    }
}
