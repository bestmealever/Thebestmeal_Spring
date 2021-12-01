package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.dto.ChoiceDto;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ChoiceService {

    private final TagRepository tagRepository;
    private final FoodRepository foodRepository;

    public void toChoiceService(ChoiceDto choiceDto) {
        List<String> categoryWant = choiceDto.getCategoryWant();
        List<String> yesterdayEat = choiceDto.getYesterdayEat();
        List<String> emotionWant = choiceDto.getEmotionWant();

        if (categoryWant == null) {
            if (yesterdayEat != null) {
                Set<String> FoodNameForCat = new HashSet<String>();
                Set<String> FoodNameForEmo = new HashSet<String>();
                List<Tag> tmpTagYesterday = tagRepository.findAllByTagNameNotIn(yesterdayEat);
                List<Tag> tmpTagEmotion = tagRepository.findAllByTagNameIn(emotionWant);

                for (int i = 0; i < tmpTagYesterday.size(); i ++) {
                    for (int j = 0; j < tmpTagYesterday.size(); j ++) {
                        String tmp = tmpTagYesterday.get(j).getFood().getName();
                        FoodNameForCat.add(tmp);
                    }
                }
                for (int i = 0; i < tmpTagEmotion.size(); i ++) {
                    for (int j = 0; j < tmpTagEmotion.size(); j ++) {
                        String tmp = tmpTagEmotion.get(j).getFood().getName();
                        FoodNameForEmo.add(tmp);
                    }
                }

                System.out.println(FoodNameForCat);
                System.out.println(FoodNameForEmo);

            } else {

            }
        } else {

        }

    }
}
