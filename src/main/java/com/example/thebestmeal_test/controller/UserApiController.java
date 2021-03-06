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

    //??????
    //????????????
    @PostMapping(value = "/signup")
    public ResponseEntity<?> createUser(@RequestBody SignupRequestDto requestDto) throws Exception {
        userService.registerUser(requestDto);
        authenticate(requestDto.getUsername(), requestDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));

    }

    //?????? ??????
    //?????????
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDto userDto) throws Exception {
        authenticate(userDto.getUsername(), userDto.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
    }


    //??????
    @PostMapping(value = "/login/kakao")
    public ResponseEntity<?> createAuthenticationTokenByKakao(@RequestBody SocialLoginDto socialLoginDto) throws Exception {
        User kakaoUser = userService.kakaoLogin(socialLoginDto);
        String username = kakaoUser.getUsername();
        String nickname = kakaoUser.getNickname();
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new KakaoJwtResponse(token, userDetails.getUsername(), nickname));

    }

//    ?????? ?????????
    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    //????????? ????????????
    @PostMapping("/signup/idcheck")
    public Boolean checkSameUsername(@RequestBody idCheckDto idDto) {
        return userService.idCheck(idDto);
    }


    //???????????????
    @GetMapping("/mypage")
    public MyPageDto getMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return recommendedService.toMyPageInfo(userDetails);
    }

    //??????????????? ?????? ????????? ??????
    @PutMapping("/mypage/statusMessage")
    public void modifyStatusMessage(@RequestBody UserStatusModifyDto statusModifyDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.modifyStatusMessage(statusModifyDto,userDetails);
//        return "????????? ?????? ??????!";
    }

    //??????????????? ????????? ?????????
    @PostMapping("/images")
    public void upload(@RequestParam("images") MultipartFile multipartFile, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        awsService.fileCheck(multipartFile, userDetails);

    }
}