package com.cho.springblog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 데이터가 아닌 파일을 리턴할 것임
public class tempControllerTest {
    @GetMapping("/temp/home")
    public String tempHome(){
        return "test";
    }
}
