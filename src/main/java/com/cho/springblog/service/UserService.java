package com.cho.springblog.service;

import com.cho.springblog.model.RoleType;
import com.cho.springblog.repository.UserRepository;
import com.cho.springblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 왜 필요한가? 1. 트랜젝션 관리 2. 서비스라는 의미
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void newmember(User user) {
        String rawPassword = user.getPassword(); // 원문
        String endPassword = encoder.encode(rawPassword); // 해쉬
        user.setPassword(endPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }



    /*@Transactional(readOnly = true) // 정합성 유지
    public User loginUser(User user){ // 전통적 방식
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
}