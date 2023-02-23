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
        System.out.println(rawPassword);
        String endPassword = encoder.encode(rawPassword); // 해쉬
        user.setPassword(endPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        // select를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서!!
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주거든요.
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());

        // 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 이 자동으로 됩니다.
        // 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
    }
}