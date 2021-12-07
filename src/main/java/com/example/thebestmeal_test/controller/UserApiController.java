package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.LikedFood;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.dto.*;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.LikedFoodRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.service.AwsService;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.service.UserService;
import com.example.thebestmeal_test.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final LikedFoodRepository likedFoodRepository;
    private final AwsService awsService;

    //로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDto userDto) throws Exception {
        authenticate(userDto.getUsername(), userDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
    }
//    @PostMapping(value = "/login/kakao")
//    public ResponseEntity<?> createAuthenticationTokenByKakao(@RequestBody SocialLoginDto socialLoginDto) throws Exception {
//        String username = userService.kakaoLogin(socialLoginDto.getToken());
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
//    }

    @PostMapping(value = "/login/kakao")
    public ResponseEntity<?> createAuthenticationTokenByKakao(@RequestBody SocialLoginDto socialLoginDto) throws Exception {
        String username = userService.kakaoLogin(socialLoginDto.getToken());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
    }

//    //카카오 로그인 callback
//    @GetMapping("/user/kakao/callback")
//    public String kakaoLogin(String code) {
//        userService.kakaoLogin(code);
//        return "redirect:/";
//    }

    //회원가입
    @PostMapping(value = "/signup")
    public ResponseEntity<?> createUser(@RequestBody SignupRequestDto userDto) throws Exception {
        userService.registerUser(userDto);
        authenticate(userDto.getUsername(), userDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
    }

    //아이디 중복확인
    @PostMapping("/signup/idcheck")
    public Boolean checkSameUsername(@RequestBody idCheckDto idDto) {
        String username = idDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        Boolean response = found.isPresent();
        return response;
    }

    //food 보여주기
//    @GetMapping("/liked")
//    public List<Food> getFoodList() {
//        return foodRepository.findTop12ByOrderByLikedFoodDesc();
//    }

    @GetMapping("/liked")
    public List<Food> getFoodList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails.getUser().getUsername());
        return foodRepository.findTop12ByOrderByLikedFoodDesc();
    }

    //likedfood 개수
    @GetMapping("/liked/count/{id}")
    public List<LikedFood> getFoodLikedCount(@PathVariable Long id) {
        return likedFoodRepository.findByFood_Id(id);
    }

    //food 보여주기
    @GetMapping("/liked/{id}")
    public Food getFood(@PathVariable Long id) {
        return foodRepository.findById(id).orElseThrow(
                () -> new NullPointerException("없습니다")
        );
    }

    //food 보여주기 로그인 사용자
    @GetMapping("/likedCheck/{id}")
    public Boolean likedCheck(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likedFoodCheckd(id,userDetails);
    }

    //좋아요
    @PostMapping("/liked/{id}")
    public Boolean updateLikeFood(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Boolean response = likedFoodCheckd(id,userDetails);
        if (response == true) {
            return response;
        } else {
            Food food = foodRepository.findById(id).orElseThrow(
                    ()->new NullPointerException("그런 음식 없어요")
            );
            User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                    ()->new NullPointerException("그런 사람 없어요")
            );
            LikedFood likedFood = new LikedFood(food,user);
            likedFoodRepository.save(likedFood);
        }
        return response;
    }

    //좋아요 취소
    @DeleteMapping("/liked/{id}")
    public String deleteLikedFood(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userRepository.findByUsername(userDetails.getUser().getUsername()).orElseThrow(
                () -> new NullPointerException("그런 사람 없는데요?"));
        Food food = foodRepository.findById(id).orElseThrow(() -> new NullPointerException("없음."));
        Optional<LikedFood> likedFoodFound = likedFoodRepository.findByUserAndFood(user,food);
        Long LikedFoodId = likedFoodFound.get().getIdx();
        likedFoodRepository.deleteById(LikedFoodId);
        return "삭제!";
    }

    //마이페이지
    @GetMapping("/mypage")
    public Optional<User> getMyinfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userRepository.findByUsername(userDetails.getUsername());
    }

    //마이페이지 상태 메세지 수정
    @PutMapping("/mypage/statusMessage")
    public String modifyStatusMessage(@RequestBody UserStatusModifyDto statusModifyDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.modifyStatusMessage(statusModifyDto,userDetails);
        return "메세지 수정 완료!";
    }

    //마이페이지 이미지 업로드
    @PostMapping("/images")
    public String upload(@RequestParam("images") MultipartFile multipartFile, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        User user = userDetails.getUser();
        awsService.upload(multipartFile, "profile_pic", user);
        return "사진 업로드 성공!";
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private Boolean likedFoodCheckd(Long id, UserDetailsImpl userDetails) {
        User user = userRepository.findByUsername(userDetails.getUser().getUsername()).orElseThrow(
                () -> new NullPointerException("그런 사람 없는데요?"));
        Food food = foodRepository.findById(id).orElseThrow(() -> new NullPointerException("없음."));
        Optional<LikedFood> likedFoodFound = likedFoodRepository.findByUserAndFood(user,food);
        Boolean response = likedFoodFound.isPresent();
        return response;
    }

}