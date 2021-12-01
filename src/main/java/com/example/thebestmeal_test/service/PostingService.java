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


    // 타입 함수명 인자

    //(1) 기존 DB에 중복 데이터 있는지 확인, else 이미 등록되어있는지 확인 (기존 DB 불러온 후 생각할 것)


    //(2) 게시글 PostingDto 저장
    //0332 없애보기
//    @Transactional
//    public Posting savePosting(PostDto postDto) {
//        Posting posting = new Posting(postDto);
//        postingRepository.save(posting);
//        return posting;
//    }

//    //(3) 음식 사진 업로드
//    public PostingFood savePosting(PostingDto postingDto) {
//        Posting posting = new Posting(postingDto);
//        postingRepository.save(posting);
//        return posting;
//    }


}
