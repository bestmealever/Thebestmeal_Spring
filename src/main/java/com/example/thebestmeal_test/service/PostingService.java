package com.example.thebestmeal_test.service;


import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.Tag;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.dto.FoodCheckDto;
import com.example.thebestmeal_test.dto.PostDto;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.PostingRepository;
import com.example.thebestmeal_test.repository.TagRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

//저장 기능만 제공

public class PostingService {

    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final FoodRepository foodRepository;



}
