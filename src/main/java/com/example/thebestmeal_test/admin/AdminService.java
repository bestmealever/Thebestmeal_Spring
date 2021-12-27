package com.example.thebestmeal_test.admin;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service

public class AdminService {
    private final FoodRepository foodRepository;
    private final PostingRepository postingRepository;

    public AdminDto toAdminPosting() {
        //posting 정보와 FOOD 정보 같이 보냄
        //postingFoods: posting 된 것 중에 food 객체만 가져옴
        //postings: posting 전체 리스트
        List<Food> postingFoods = new ArrayList<>();
        List<Posting> postings = postingRepository.findAllByStatusIsNull();
//      List<Posting> postings = postingRepository.findAll();
      for (Posting posting: postings) {
          postingFoods.add(posting.getFood());
      }
//        System.out.println(postingFoods);

        //postingAcceptedFoods: postingAccepted 된 것 중에 food 객체만
        //postingAcceptedList:postingAccepted 리스트
        List<Food> postingAcceptedFoods = new ArrayList<>();
        List<Posting> postingAcceptedList = postingRepository.findAllByStatus("accepted");
//        List<Posting> postingAcceptedList = postingRepository.findAll();
        for (Posting posting: postingAcceptedList) {
            postingAcceptedFoods.add(posting.getFood());
        }

        //postingDeclinedFoods: postingDeclined 된 것중에 food 객체만.
        List<Food> postingDeclinedFoods = new ArrayList<>();
        List<Posting> postingDeclinedList = postingRepository.findAllByStatus("declined");
//        List<Posting> postingDeclinedList = postingRepository.findAll();
        for (Posting posting: postingDeclinedList) {
            postingDeclinedFoods.add(posting.getFood());
        }

        AdminDto dto = new AdminDto(postings, postingAcceptedList, postingDeclinedList, postingFoods, postingAcceptedFoods, postingDeclinedFoods);
        return dto;
    }
}