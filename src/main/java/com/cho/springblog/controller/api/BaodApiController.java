package com.cho.springblog.controller.api;

import com.cho.springblog.DTO.ResponseDto;
import com.cho.springblog.config.auth.PrincipalDetail;
import com.cho.springblog.model.Board;
import com.cho.springblog.service.BoadService;
import com.cho.springblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaodApiController {
    @Autowired
    private BoadService boadService;

    @PostMapping("/api/boad")
    public ResponseDto<Integer> save (@RequestBody Board boad, @AuthenticationPrincipal PrincipalDetail principal){ // 유저네임, 패스워드, 이메일을 받음. but 유저는 더 많은 정보를 가짐 때문에 여기서 추가를 해줌
        // 실제로 DB에 insert를 하고 아래에서 return
        boadService.Write(boad, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }


    @DeleteMapping("/api/boad/{id}")
    public ResponseDto<Integer> deletebyId(@PathVariable int id){
        boadService.ContentDelete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/api/boad/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board boad){
        boadService.update(id, boad);
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
