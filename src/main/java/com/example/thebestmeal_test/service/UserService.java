package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.domain.Food;
import com.example.thebestmeal_test.domain.User;
import com.example.thebestmeal_test.domain.UserRole;
import com.example.thebestmeal_test.dto.*;
import com.example.thebestmeal_test.kakao.KakaoOAuth2;
import com.example.thebestmeal_test.kakao.KakaoUserInfo;
import com.example.thebestmeal_test.repository.FoodRepository;
import com.example.thebestmeal_test.repository.UserRepository;
import com.example.thebestmeal_test.security.UserDetailsImpl;
import com.example.thebestmeal_test.security.UserDetailsServiceImpl;
import com.example.thebestmeal_test.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final AuthenticationManager authenticationManager;
    private final KakaoOAuth2 kakaoOAuth2;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    //?????? ??????
    public void registerUser(SignupRequestDto requestDto) throws Exception {
        String username = requestDto.getUsername();
        // ?????? ID ?????? ??????
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("????????? ????????? ID ??? ???????????????.");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        // ????????? ROLE ??????
        UserRole role = UserRole.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("????????? ????????? ?????? ????????? ??????????????????.");
            }
            role = UserRole.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);

//        return user;

    }

    //????????? ?????????
    public User kakaoLogin(SocialLoginDto socialLoginDto) throws Exception {
        // ????????? OAuth2 ??? ?????? ????????? ????????? ?????? ??????
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(socialLoginDto.getToken());
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();

        // username ??? nickname ??? ?????? ????????? KakaoId ??? ??????
        String username = Long.toString(kakaoId);

        // ???????????? = ????????? Id + ADMIN TOKEN
        String password = kakaoId + ADMIN_TOKEN;

        // DB ??? ????????? Kakao Id ??? ????????? ??????
        User kakaoUser = userRepository.findByKakaoId(kakaoId)
                .orElse(null);

        // ????????? ????????? ????????????
        if (kakaoUser == null) {
            // ???????????? ?????????
            String encodedPassword = passwordEncoder.encode(password);
            // ROLE = ?????????
            UserRole role = UserRole.USER;
            kakaoUser = new User(nickname, username, encodedPassword, email, role, kakaoId);
//            kakaoUser = new User(nickname, encodedPassword, email, role, kakaoId);
            userRepository.save(kakaoUser);
        }

        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return kakaoUser;
    }

    //????????? ?????? ??????
    public Boolean idCheck(idCheckDto idDto) {
        String username = idDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        Boolean response = found.isPresent();
        return response;
    }



    //??????????????? ?????? ????????? ??????
    public void modifyStatusMessage(UserStatusModifyDto statusModifyDto, UserDetailsImpl userDetails) {
        User found = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("?????? ?????? ?????????????"));
        found.update(statusModifyDto);
        userRepository.save(found);
    }


    //??????????????? ????????? ?????????
    public void updateProfileImg(String uploadImageUrl, User user) {
        User found = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new NullPointerException("?????? ?????? ?????????????"));
        System.out.println(found.getProfilePhoto()); //????????? ?????? -> /images/profile_pic.jpg
        found.update(uploadImageUrl); //???????????? ??????
        System.out.println(uploadImageUrl); //???????????? ?????? url ??????
        System.out.println(found.getProfilePhoto());
        userRepository.save(found);
    }
}
