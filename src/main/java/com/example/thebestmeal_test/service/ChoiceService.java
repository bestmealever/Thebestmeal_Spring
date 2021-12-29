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
import java.util.Random;
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
            //1(카테고리) - 선택안함
            if (yesterdayEat != null) {
                //어제 뭐먹었는지 선택함
                List<String> FoodNameForCat = new ArrayList<>();
                List<String> FoodNameForEmo = new ArrayList<>();
                //어제 먹은 것에서는 제외
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
                //1(카테고리) - 선택안함 / 2(어제) - 선택안함
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
            //1. 카테고리 선택함
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
            resultList.addAll(noDup(FoodNameForCat, FoodNameForEmo));
        }

        // ObjectMapper mapper = new ObjectMapper();
        // 특정 필드만 보내고 싶으면 modelmapper and dto

        System.out.println(resultList);

        if (resultList.isEmpty()) {
            System.out.println("empty");
            return null;
        } else {
            return foodRepository.findAllByNameIn(resultList);
        }
    }

    //중복 제거
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

//    public void toChoiceServiceNoFeel(ChoiceDto choiceDto) {
//        List<String> categoryWant = choiceDto.getCategoryWant();
//        List<String> yesterdayEat = choiceDto.getYesterdayEat();
//        List<String> emotionWant = choiceDto.getEmotionWant();
//
//        List<String> resultList = new ArrayList<>();
//        if (categoryWant == null && yesterdayEat == null && emotionWant == null) {
//            Random ran = new Random();
//            System.out.println("랜덤" + (ran.nextInt(10)+1));
//            //food 데이터를 identity로 재설정 한뒤 landom 값을 추출해서 그 숫자로 food를 찾아 보여줌. ㅇ어떤지...?
//        }
//        if (categoryWant == null && yesterdayEat != null && emotionWant == null) {
//            System.out.println("어제 먹은 음식을 제외한 모든 음식 중에 랜덤");
//            //resultList.addAll(모아진 음식 변수명.stream().distinct().collect(Collectors.toList()));
//        }
//        if (categoryWant != null && emotionWant == null) {
//            System.out.println("카테고리안의 음식 중에 랜덤");
//            //resultList.addAll(모아진 음식 변수명.stream().distinct().collect(Collectors.toList()));
//        }
//    }
}