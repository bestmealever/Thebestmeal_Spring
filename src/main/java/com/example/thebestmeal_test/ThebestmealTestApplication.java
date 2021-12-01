package com.example.thebestmeal_test;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EnableJpaAuditing
@SpringBootApplication
public class ThebestmealTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThebestmealTestApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner demo(FoodRepository foodRepository,TagRepository tagRepository) {
//        return (args) -> {
//
//            String path = "/Users/macbookpro/IdeaProjects/Thebestmeal_Spring6/src/main/resources/"; // 현재폴더의 디렉토리 가지고 오기.
//            File title = new File(path + "food_final_title.csv");
//            File url = new File(path + "food_final_url.csv");
//            File emotion = new File(path + "food_final_emotion.csv");
//            File category = new File(path + "food_final_category.csv");
//
//            BufferedReader br1 = new BufferedReader(new BufferedReader(new FileReader(title))); //버퍼리더 만들기.
//            BufferedReader br2 = new BufferedReader(new BufferedReader(new FileReader(url)));
//            BufferedReader br3 = new BufferedReader(new BufferedReader(new FileReader(emotion)));
//            BufferedReader br4 = new BufferedReader(new BufferedReader(new FileReader(category)));
//
//            String titleLine;
//            String urlLine;
//            String emotionLine;
//            String categoryLine;
//
//            int a = 109;
//
//            for (int i=0; i<a; i++ ) {
//                titleLine = br1.readLine();
//                urlLine = br2.readLine();
//                emotionLine = br3.readLine();
//                categoryLine = br4.readLine();
//                //food
//                Food food = new Food(titleLine,urlLine);
//                foodRepository.save(food);
//                //category
//                List<String> items1 = Arrays.asList(emotionLine.split("\\s*,\\s*"));
//                List<Tag> tags1 = items1.stream().map(tag -> new Tag(food, tag,"emotion")).collect(Collectors.toList());
//                tagRepository.saveAll(tags1);
//                //emotion
//                List<String> items2 = Arrays.asList(categoryLine.split("\\s*,\\s*"));
//                List<Tag> tags2 = items2.stream().map(tag -> new Tag(food, tag,"category")).collect(Collectors.toList());
//                tagRepository.saveAll(tags2);
//            }
//        };
//    }
}
