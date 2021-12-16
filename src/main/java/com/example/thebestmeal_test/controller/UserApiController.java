package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.LikedFood;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.dto.*;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.LikedFoodRepository;
import com.example.thebestmeal_test.repository.RecommendedRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.service.AwsService;
import com.example.thebestmeal_test.service.LikedService;
import com.example.thebestmeal_test.service.RecommendedService;
import com.example.thebestmeal_test.service.UserService;
import com.example.thebestmeal_test.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
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

import javax.transaction.Transactional;
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
    private final RecommendedRepository recommendedRepository;
    private final RecommendedService recommendedService;
    private final LikedService likedService;

    //리팩
    //회원가입
    @PostMapping(value = "/signup")
    public ResponseEntity<?> createUser(@RequestBody SignupRequestDto requestDto) throws Exception {
        return userService.registerUser(requestDto);

    }

    //리팩 완료
    //로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDto userDto) throws Exception {
        return userService.toCreateAuthenticationToken(userDto);
    }


    //리팩
    @PostMapping(value = "/login/kakao")
    public ResponseEntity<?> createAuthenticationTokenByKakao(@RequestBody SocialLoginDto socialLoginDto) throws Exception {
        return userService.kakaoLogin(socialLoginDto);
    }


    //아이디 중복확인
    @PostMapping("/signup/idcheck")
    public Boolean checkSameUsername(@RequestBody idCheckDto idDto) {
        return userService.idCheck(idDto);
    }


    //마이페이지
    @GetMapping("/mypage")
    public MyPageDto getMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return recommendedService.toMyPageInfo(userDetails);
    }

    //마이페이지 상태 메세지 수정
    @PutMapping("/mypage/statusMessage")
    public String modifyStatusMessage(@RequestBody UserStatusModifyDto statusModifyDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.modifyStatusMessage(statusModifyDto,userDetails);
        return "메세지 수정 완료!";
    }

    //마이페이지 이미지 업로드
    @PostMapping("/images")
    public String upload(@RequestParam("images") MultipartFile multipartFile, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        try {
            awsService.upload(multipartFile, "profile_pic", userDetails.getUser());
        } catch (FileSizeLimitExceededException e) {
            throw new Exception("파일 사이즈 초과", e);
        }
        return "사진 업로드 성공!";
    }
}