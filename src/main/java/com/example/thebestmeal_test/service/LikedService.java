package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.LikedFood;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.LikedFoodRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikedService {
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final LikedFoodRepository likedFoodRepository;
    private final UserService userService;

    @Transactional
    public Boolean liked(Long id, UserDetailsImpl userDetails) {
        Boolean response = likedFoodCheckd(id,userDetails);
        if (response == true) {
            return true;
        } else {
            Food food = foodRepository.findById(id).orElseThrow(
                    ()->new NullPointerException("그런 음식 없어요")
            );
            User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                    ()->new NullPointerException("그런 사람 없어요")
            );
            LikedFood likedFood = new LikedFood(food,user);
            likedFoodRepository.save(likedFood);
            updateCnt(id);
        }
        return false;
    }

    @Transactional
    public void unLiked(Long id, UserDetailsImpl userDetails) {
        User user = userRepository.findByUsername(userDetails.getUser().getUsername()).orElseThrow(
                () -> new NullPointerException("그런 사람 없는데요?"));
        Food food = foodRepository.findById(id).orElseThrow(() -> new NullPointerException("없음."));
        Optional<LikedFood> likedFoodFound = likedFoodRepository.findByUserAndFood(user,food);
        Long LikedFoodId = likedFoodFound.get().getIdx();
        likedFoodRepository.deleteById(LikedFoodId);
        updateCntM(id);
    }

    @Transactional
    public Boolean likedFoodCheckd(Long id, UserDetailsImpl userDetails) {
        User user = userRepository.findByUsername(userDetails.getUser().getUsername()).orElseThrow(
                () -> new NullPointerException("그런 사람 없는데요?"));
        Food food = foodRepository.findById(id).orElseThrow(() -> new NullPointerException("없음."));
        Optional<LikedFood> likedFoodFound = likedFoodRepository.findByUserAndFood(user,food);
        Boolean response = likedFoodFound.isPresent();
        return response;
    }

    @Transactional
    public void updateCnt(Long id) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new NullPointerException("굿"));
        food.update(+1);
        foodRepository.save(food);
    }
    @Transactional
    public void updateCntM(Long id) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new NullPointerException("굿"));
        food.update(-1);
        foodRepository.save(food);
    }
}