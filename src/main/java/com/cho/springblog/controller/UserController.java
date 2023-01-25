package com.cho.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// 인증이 안된 사용자들이 출입할 수 있는 경로는 auth가 써있는 경로만 가능하다.
// 그냥 주소가 / 이면 index.jsp. 두 경우만 허용한다.
// static 이하에 있는 리소스 파일들은 js, css, image 허용
@Controller
public class UserController {
    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }
}
