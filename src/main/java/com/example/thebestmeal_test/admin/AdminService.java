package com.example.thebestmeal_test.admin;

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
        List<Posting> postings = postingRepository.findAllByStatusIsNull();
//        String status =
//        List<Posting> postingsAccepted = postingRepository.findAllByStatus(posting.getStatus());
        List<Posting> postingsAccepted = postingRepository.findAllByStatus("accepted");
        AdminDto dto = new AdminDto(postings, postingsAccepted);
        return dto;
    }
}
