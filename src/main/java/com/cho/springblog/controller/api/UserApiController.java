package com.cho.springblog.controller.api;

import com.cho.springblog.service.UserService;
import com.cho.springblog.DTO.ResponseDto;
import com.cho.springblog.model.RoleType;
import com.cho.springblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save (@RequestBody User user){ // 유저네임, 패스워드, 이메일을 받음. but 유저는 더 많은 정보를 가짐 때문에 여기서 추가를 해줌
        // 실제로 DB에 insert를 하고 아래에서 return
        userService.newmember(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }


    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) { // key=value, x-www-form-urlencoded
        userService.updateUser(user);
        // 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.
        // 세션 등록

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
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
