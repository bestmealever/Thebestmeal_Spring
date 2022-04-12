package com.example.thebestmeal_test.domain;

import com.example.thebestmeal_test.dto.StatusAlarmDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class StatusAlarm extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageUrl;

    public StatusAlarm(StatusAlarmDto.Request request, String imageUrl) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.imageUrl = imageUrl;
    }
}
