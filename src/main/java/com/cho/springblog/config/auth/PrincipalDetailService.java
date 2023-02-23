package com.cho.springblog.config.auth;

import com.cho.springblog.model.User;
import com.cho.springblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 스프링이 로그인 요청을 가로챌 때 유저네임이라는 변수와 유저패스워드 변수를 가로채는데 이 패스워드 부분 처리는 알아서 해준다.
        // 해당 유저 네임이 디비에 있는지만 확인하여 리턴
        User principal = userRepository.findByUsername(username).orElseThrow(()->{
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 : "+username);
        });

        System.out.println("로그인이 왜 안되는거야 씨발");
        return new PrincipalDetail(principal); // 이것이 시큐리티 세션에 저장 아이디는 유저
    }
}
