package com.cho.springblog.test;

import org.springframework.web.bind.annotation.*;
// HTML파일 을 응답하려면?
// @Controller

@RestController // 사용자가 요청할시 응답을 해주는 아이 -> 어떤 응답? 데이터를 응답
public class HttpControllerTest {
    private static final String TAG = "HTTPControllerTest : ";
    @GetMapping("/http/Lombok")
    public String LombokTest(){
        Member m = Member.builder().username("cho").password("456").email("@naver").build();
        System.out.println(TAG+"getter : "+m.getUsername());
        m.setUsername("CAU");
        System.out.println(TAG+"setter : "+m.getUsername());
        return "Lombok 요청";
    }

    @GetMapping("/http/get")
    public String getTest(Member m){
        System.out.println(TAG+"getter : "+m.getId());
        m.setId(5000);
        System.out.println(TAG+"getter : "+m.getId());
        //Member m1 = Member.builder().username("cho").password("456").email("naver").build();
        return "get 요청 : "+ m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
    }
    @PostMapping("/http/post") // 인터넷 브라우저 요청은 get만 할 수 있다!!
    public String postTest(@RequestBody Member m){   // 바디의 데이터를 받는 법 , raw data = text/pain 을 보냈음
        return "post 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + ", " + m.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        return "put 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + ", " + m.getEmail();
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
