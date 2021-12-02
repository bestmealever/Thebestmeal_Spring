package com.example.thebestmeal_test.service;


import com.example.thebestmeal_test.repository.PostingRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

//저장 기능만 제공

public class PostingService {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

//        System.out.println

}
