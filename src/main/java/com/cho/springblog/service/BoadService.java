package com.cho.springblog.service;

import com.cho.springblog.model.Board;
import com.cho.springblog.model.RoleType;
import com.cho.springblog.model.User;
import com.cho.springblog.repository.BoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 왜 필요한가? 1. 트랜젝션 관리 2. 서비스라는 의미
public class BoadService {
    @Autowired
    private BoadRepository boadRepository;

    @Transactional
    public void Write(Board boad, User user) { // 타이틀, 컨텐트
        boad.setCount(0);
        boad.setUser(user);
        boadRepository.save(boad);
    }

    public Page<Board> ContentList(Pageable pageable){
        return boadRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Board ShowDetail(int id){
        return boadRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 상세보기 실패!");
        });
    }

    @Transactional
    public void ContentDelete(int id){
        boadRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Board requestBoad){
        Board boad = boadRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글찾기 아이디 못찾음 실패!");
        });
        boad.setTitle(requestBoad.getTitle());
        boad.setContent(requestBoad.getContent());
        System.out.println(boad.getTitle());
        System.out.println(boad.getContent());
        // 해당 함수 종료시 트랜젝션이 서비스가 종료될 때 자동 업데이트
    }

    /*@Transactional(readOnly = true) // 정합성 유지
    public User loginUser(User user){ // 전통적 방식
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
}