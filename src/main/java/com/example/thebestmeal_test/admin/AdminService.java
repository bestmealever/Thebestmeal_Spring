package com.example.thebestmeal_test.admin;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.Posting;
import com.example.thebestmeal_test.domain.PostingStatus;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service

public class AdminService {
//    private final FoodRepository foodRepository;
    private final PostingRepository postingRepository;

    public AdminDto toAdminPosting() {
        //posting 정보와 FOOD 정보 같이 보냄
        //postingFoods: posting 된 것 중에 food 객체만 가져옴
        //postings: posting 전체 리스트
        List<Food> postingFoods = new ArrayList<>();
        List<Posting> postings = postingRepository.findAllByStatus(PostingStatus.ToBeReviewed);
//        List<Posting> postings = postingRepository.findAllByPostingStatus(PostingStatus.ToBeReviewed);
//        List<Posting> postings = postingRepository.findAllByStatus("ToBeReviewed");
//      List<Posting> postings = postingRepository.findAll();
      for (Posting posting: postings) {
          postingFoods.add(posting.getFood());
      }
//        System.out.println(postingFoods);

        //postingAcceptedFoods: postingAccepted 된 것 중에 food 객체만
        //postingAcceptedList:postingAccepted 리스트
        List<Food> postingAcceptedFoods = new ArrayList<>();
        List<Posting> postingAcceptedList = postingRepository.findAllByStatus(PostingStatus.Accepted);
//        List<Posting> postingAcceptedList = postingRepository.findAllByPostingStatus(PostingStatus.Accepted);
//        List<Posting> postingAcceptedList = postingRepository.findAll();
        for (Posting posting: postingAcceptedList) {
            postingAcceptedFoods.add(posting.getFood());
        }

        //postingDeclinedFoods: postingDeclined 된 것중에 food 객체만.
        List<Food> postingDeclinedFoods = new ArrayList<>();
        List<Posting> postingDeclinedList = postingRepository.findAllByStatus(PostingStatus.Rejected);
//        List<Posting> postingDeclinedList = postingRepository.findAllByPostingStatus(PostingStatus.Rejected);
//        List<Posting> postingDeclinedList = postingRepository.findAll();
        for (Posting posting: postingDeclinedList) {
            postingDeclinedFoods.add(posting.getFood());
        }

        AdminDto dto = new AdminDto(postings, postingAcceptedList, postingDeclinedList, postingFoods, postingAcceptedFoods, postingDeclinedFoods);
        return dto;
    }

    public void toUpdateAccept(Long id) {
        //Posting ID 를
        //foodcard에는 posting이 들어와있는 상태임. 그 foodcard의 posting 값을 업데이트.
        //저장이 되는 db는 postingdb가 될 것.
        Posting posting = postingRepository.findById(id).orElseThrow(()-> new NullPointerException("그런 포스팅 없어요"));
        posting.update(PostingStatus.Accepted);
        postingRepository.save(posting);
    }
}
