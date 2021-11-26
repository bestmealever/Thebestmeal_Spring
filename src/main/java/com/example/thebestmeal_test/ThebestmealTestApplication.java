package com.example.thebestmeal_test;

import com.example.thebestmeal_test.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ThebestmealTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThebestmealTestApplication.class, args);

    }

}
