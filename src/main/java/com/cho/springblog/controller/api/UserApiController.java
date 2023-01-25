package com.cho.springblog.controller.api;

import com.cho.springblog.service.UserService;
import com.cho.springblog.DTO.ResponseDto;
import com.cho.springblog.model.RoleType;
import com.cho.springblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {
    @Autowired
    private UserService userService;


    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save (@RequestBody User user){ // 유저네임, 패스워드, 이메일을 받음. but 유저는 더 많은 정보를 가짐 때문에 여기서 추가를 해줌
        // 실제로 DB에 insert를 하고 아래에서 return
        userService.newmember(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }







    /* 전통적인 방식
    @PostMapping("/api/login")
    public ResponseDto<Integer> login (@RequestBody User user, HttpSession session){
        System.out.println("로그인 api");
        userService.loginUser(user);
        User principal = userService.loginUser(user);
        if(principal != null){
            session.setAttribute("principal", principal); // 세션에 올림?
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }*/
}
