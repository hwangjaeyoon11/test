package com.mbc.day03.service;


import com.google.gson.Gson;
import com.mbc.day03.Day03Application;
import com.mbc.day03.domain.MemberVo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.management.monitor.StringMonitor;
import java.awt.geom.RectangularShape;
import java.util.Map;


@Service
public class KakaoLoginService {
    private final DefaultAuthenticationEventPublisher authenticationEventPublisher;
    private final Day03Application day03Application;

    public KakaoLoginService(DefaultAuthenticationEventPublisher authenticationEventPublisher, Day03Application day03Application) {
        this.authenticationEventPublisher = authenticationEventPublisher;
        this.day03Application = day03Application;
    }

    public String getAccessToken(String code) {
        // Header 생성
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // httpBody 생성
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("grant_type", "authorization_code");
        body.add("client_id", "ca15d844866d4c035493742bd9d3dc79");
        body.add("redirect_url", "http://localhost:8888/kakao/auth");
        body.add("code", code);

        // header와 body가 설정 된 HttpEntity 객체 생성
        // HttpEntity : ResponseBody와 기능이 비슷한 객체이지만
        // 헤더 정보를 포함하는 객체

        HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, header);

        // 웨[ㅂ브라우저 역할을 하는 객체, 브라우저 없이 Http 요청을 할 수 있는 객체
        RestTemplate restTemplate = new RestTemplate();

        // exchange() : 인증서버에 요청 및 응답(토큰) 받기
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                // 토큰 요청 주소 설정
                "https://kauth.kakao.com/oauth/token", HttpMethod.POST, // 요청방식
                requestEntity, // Http 요청 메세지
                String.class // 응답 받을 타입
        );

//        return responseEntity.getBody();
        // Hearder와 body 중에 body만 가져오기
        String tokenInfo = responseEntity.getBody();

        // 엑세스 토큰만 추출
        Gson gson = new Gson();

        // json형태의 문자열을 Map타입의 구조로 변경
        Map map = gson.fromJson(tokenInfo, Map.class);

        return map.get("access_token").toString();

        public MemberVo getUserinfo (String accessToken){
            // ------------ http 요청 메시지 시작 ----------------
            // 헤더 설정
            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer");
            header.add("Content-Type", "application/Bearer ${ACCESS_TOKEN}");

            // body 생략
            HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(header);

            // ----
        }

    }
}
