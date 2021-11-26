package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.dto.ChoiceDto;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoiceService {

    private final FoodRepository foodRepository;
    private final TagRepository tagRepository;

    public void getChoices(ChoiceDto choiceDto) {

        List<String> foodList;

        if (choiceDto.getCategoryWant() != null) {

            System.out.println(choiceDto.getCategoryWant());
            System.out.println(choiceDto.getCategoryWant().get(0).getClass().getName());

            for (int i = 0; i < choiceDto.getCategoryWant().size(); i++) {
                String tagChoice = choiceDto.getCategoryWant().get(i);
//                System.out.println(tagRepository.findAllByTagName(tagChoice));
                List<Tag> tagNameList = tagRepository.findAllByTagName(tagChoice);
                System.out.println(tagNameList);
                System.out.println(tagNameList.get(0).getTagName());
                System.out.println(tagNameList.get(0).getFood());
                System.out.println(tagNameList.get(0).getFood().getName());

            }
        }




        System.out.println(choiceDto.getYesterdayEat());
        System.out.println(choiceDto.getEmotionWant());


    }


}
