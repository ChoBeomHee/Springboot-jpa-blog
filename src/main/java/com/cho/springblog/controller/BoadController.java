package com.cho.springblog.controller;

import com.cho.springblog.model.Board;
import com.cho.springblog.service.BoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoadController {
    @Autowired
    private BoadService boadService;
    @GetMapping("/")
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("boads", boadService.ContentList(pageable)); // 글 목록을 boads에 저장, Controller. viewResolver 작동
        return "index";
    }

    @GetMapping("/boad/saveForm")
    public String saveForm(){
        return "boad/saveForm";
    }

    @GetMapping("/boad/{id}")
    public String findById(@PathVariable int id, Model model){
        model.addAttribute("boad", boadService.ShowDetail(id));
        return "boad/detail";
    }

    @GetMapping("/boad/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        System.out.println("여긴되나");
        model.addAttribute("boad", boadService.ShowDetail(id));
        return "boad/updateForm"; // jsp
    }
}
