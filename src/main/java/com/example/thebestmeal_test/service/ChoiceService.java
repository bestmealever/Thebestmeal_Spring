package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.dto.ChoiceDto;
import com.example.thebestmeal_test.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChoiceService {

    private final TagRepository tagRepository;

    public void toChoiceService(ChoiceDto choiceDto) {
        List<String> categoryWant = choiceDto.getCategoryWant();
        List<String> yesterdayEat = choiceDto.getYesterdayEat();
        List<String> emotionWant = choiceDto.getEmotionWant();

//        if (categoryWant == null) {
//            if (yesterdayEat != null) {
//                List<Tag> yesterday;
//                for (int i = 0; i < yesterdayEat.size(); i ++) {
////                    List<Tag> tmpTag = tagRepository.findAllByTagName(yesterdayEat.get(i));
//                    yesterday.add(tagRepository.findAllByTagName(yesterdayEat.get(i)))
//                }
//            } else {
//
//            }
//        } else {
//
//        }

        List<Tag> wants = tagRepository.findAllByTagName(categoryWant.get(0));

        System.out.println(wants);

        System.out.println(wants.get(0));

        Tag test = wants.get(0);

        System.out.println(test.getFood().getName());

        System.out.println(categoryWant);
        System.out.println(yesterdayEat);
        System.out.println(emotionWant);
    }
}
