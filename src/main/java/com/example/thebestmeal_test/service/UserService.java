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

    //회원 가입
    public void registerUser(SignupRequestDto requestDto) throws Exception {
        String username = requestDto.getUsername();
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        // 사용자 ROLE 확인
        UserRole role = UserRole.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRole.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);

//        return user;

    }

    //카카오 로그인
    public User kakaoLogin(SocialLoginDto socialLoginDto) throws Exception {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(socialLoginDto.getToken());
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();

        // username 을 nickname 이 아닌 고유값 KakaoId 로 지정
        String username = Long.toString(kakaoId);

        // 패스워드 = 카카오 Id + ADMIN TOKEN
        String password = kakaoId + ADMIN_TOKEN;

        // DB 에 중복된 Kakao Id 가 있는지 확인
        User kakaoUser = userRepository.findByKakaoId(kakaoId)
                .orElse(null);

        // 카카오 정보로 회원가입
        if (kakaoUser == null) {
            // 패스워드 인코딩
            String encodedPassword = passwordEncoder.encode(password);
            // ROLE = 사용자
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

    //아이디 중복 확인
    public Boolean idCheck(idCheckDto idDto) {
        String username = idDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        Boolean response = found.isPresent();
        return response;
    }

    //인증 메서드
    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    //마이페이지 상태 메세지 수정
    public void modifyStatusMessage(UserStatusModifyDto statusModifyDto, UserDetailsImpl userDetails) {
        User found = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("그런 사람 없는데요?"));
        found.update(statusModifyDto);
        userRepository.save(found);
    }

    //마이페이지 이미지 업로드
    public void updateProfileImg(String uploadImageUrl, User user) {
        User found = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new NullPointerException("그런 사람 없는데요?"));
        System.out.println(found.getProfilePhoto()); //기본값 출력 -> /images/profile_pic.jpg
        found.update(uploadImageUrl); //업데이트 해라
        System.out.println(uploadImageUrl); //업데이트 재료 url 출력
        System.out.println(found.getProfilePhoto());
        userRepository.save(found);
    }
}
