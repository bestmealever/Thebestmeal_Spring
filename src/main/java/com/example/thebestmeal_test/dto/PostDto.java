package com.example.thebestmeal_test.dto;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private String postingFoodName;
    private List<String> postingEmo;
    private List<String> postingCat;
    private MultipartFile foodImg;
    private String postingMemo;

}
