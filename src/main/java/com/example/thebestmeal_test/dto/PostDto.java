package com.example.thebestmeal_test.dto;

import com.example.thebestmeal_test.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
public class PostDto {
    private String postingFoodName;
    private List<String> postingEmo;
    private List<String> postingCat;
    private MultipartFile foodImgUrl;
    private String postingMemo;

    public PostDto() {
    }

    public String getPostingFoodName() {
        return this.postingFoodName;
    }

    public List<String> getPostingEmo() {
        return this.postingEmo;
    }

    public List<String> getPostingCat() {
        return this.postingCat;
    }

    public MultipartFile getFoodImgUrl() {
        return this.foodImgUrl;
    }

    public String getPostingMemo() {
        return this.postingMemo;
    }

    public void setPostingFoodName(final String postingFoodName) {
        this.postingFoodName = postingFoodName;
    }

    public void setPostingEmo(final List<String> postingEmo) {
        this.postingEmo = postingEmo;
    }

    public void setPostingCat(final List<String> postingCat) {
        this.postingCat = postingCat;
    }

    public void setFoodImgUrl(final MultipartFile foodImgUrl) {
        this.foodImgUrl = foodImgUrl;
    }

    public void setPostingMemo(final String postingMemo) {
        this.postingMemo = postingMemo;
    }
}
