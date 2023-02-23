package com.cho.springblog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

// 인증이 안된 사용자들이 출입할 수 있는 경로는 auth가 써있는 경로만 가능하다.
// 그냥 주소가 / 이면 index.jsp. 두 경우만 허용한다.
// static 이하에 있는 리소스 파일들은 js, css, image 허용
@Controller
public class UserController {
    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) { // Data르 받음
        // POST 방식으로 key-value 데이터를 요청
        // OKHttp
        // Retrofit2
        // RestTemplate http 요청 함수들
        // http 헤더
        RestTemplate rt = new RestTemplate(); // http 요청이 쉬움
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        // 키 형태라고 알려줌

        //http 바디
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "3f4eceacbf932219621cc4912af17158");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);


        //헤더와 바디를 오브젝트에 담기
        //exchange 라는 함수가 httpentiti를 받게 되어있음
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers); // 바디값과 헤더를갖고 있는 엔티티
        
        // htttp 요청하기 - Post 방식으로 그리고 reponse 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        return "카카오 토큰 완료 : "+ response ;
    }

}
