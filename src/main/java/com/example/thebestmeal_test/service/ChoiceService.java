package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.dto.ChoiceDto;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChoiceService {

    private final TagRepository tagRepository;
    private final FoodRepository foodRepository;

    public List<Food> toChoiceService(ChoiceDto choiceDto) {
        List<String> categoryWant = choiceDto.getCategoryWant();
        List<String> yesterdayEat = choiceDto.getYesterdayEat();
        List<String> emotionWant = choiceDto.getEmotionWant();

        List<String> resultList = new ArrayList<>();

        if (categoryWant == null) {
            if (yesterdayEat != null) {
                List<String> FoodNameForCat = new ArrayList<>();
                List<String> FoodNameForEmo = new ArrayList<>();
                List<Tag> tmpTagYesterday = tagRepository.findAllByTagNameNotIn(yesterdayEat);
                List<Tag> tmpTagEmotion = tagRepository.findAllByTagNameIn(emotionWant);

                for (int i = 0; i < tmpTagYesterday.size(); i++) {
                    for (Tag tag : tmpTagYesterday) {
                        String tmp = tag.getFood().getName();
                        FoodNameForCat.add(tmp);
                    }
                }
                for (int i = 0; i < tmpTagEmotion.size(); i++) {
                    for (Tag tag : tmpTagEmotion) {
                        String tmp = tag.getFood().getName();
                        FoodNameForEmo.add(tmp);
                    }
                }
                resultList.addAll(noDup(FoodNameForCat, FoodNameForEmo));
            } else {
                List<String> FoodNameForEmo = new ArrayList<>();
                List<Tag> tmpTagEmotion = tagRepository.findAllByTagNameIn(emotionWant);
                for (int i = 0; i < tmpTagEmotion.size(); i++) {
                    for (Tag tag : tmpTagEmotion) {
                        String tmp = tag.getFood().getName();
                        FoodNameForEmo.add(tmp);
                    }
                }

                resultList.addAll(FoodNameForEmo.stream().distinct().collect(Collectors.toList()));
            }
        } else {
            List<String> FoodNameForCat = new ArrayList<>();
            List<String> FoodNameForEmo = new ArrayList<>();
            List<Tag> tmpTagWant = tagRepository.findAllByTagNameIn(categoryWant);
            List<Tag> tmpTagEmotion = tagRepository.findAllByTagNameIn(emotionWant);

            for (int i = 0; i < tmpTagWant.size(); i++) {
                for (Tag tag : tmpTagWant) {
                    String tmp = tag.getFood().getName();
                    FoodNameForCat.add(tmp);
                }
            }
            for (int i = 0; i < tmpTagEmotion.size(); i++) {
                for (Tag tag : tmpTagEmotion) {
                    String tmp = tag.getFood().getName();
                    FoodNameForEmo.add(tmp);
                }
            }

            System.out.println(resultList);
            resultList.addAll(noDup(FoodNameForCat, FoodNameForEmo));
        }

        // ObjectMapper mapper = new ObjectMapper();
        // 특정 필드만 보내고 싶으면 modelmapper and dto

        return foodRepository.findAllByNameIn(resultList);
    }


    public static List<String> noDup(List<String> var1, List<String> var2) {
        List<String> resultList = new ArrayList<>();
        for (String s : var1) {
            for (String value : var2) {
                if (Objects.equals(s, value)) {
                    resultList.add(s);
                    break;
                }
            }
        }
        return resultList.stream().distinct().collect(Collectors.toList());
    }
}