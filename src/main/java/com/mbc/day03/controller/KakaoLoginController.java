package com.mbc.day03.controller;

import com.mbc.day03.service.KakaoLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class KakaoLoginController {
    private KakaoLoginService kakaoLoginService;

    @GetMapping("/kakao/auth")
    public String kakaoCallback(String code, HttpSession session){
        // 1. 인증 서버로부터 인가 코드 받기 code
        System.out.println("인가 code :" + code);

        // 2. 액세스 토큰 가져오기
        String accessToken = kakaoLoginService.getAccessToken(code);
        System.out.println("accessToken :" + accessToken);

        // 3. 사용자 정보 가져오기

        // 4. 회원 가입

        // 5. 세션 등록

        return "redirect:/";
    }

}