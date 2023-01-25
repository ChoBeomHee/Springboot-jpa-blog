package com.cho.springblog.test;

import com.cho.springblog.repository.UserRepository;
import com.cho.springblog.model.RoleType;
import com.cho.springblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {
    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return "삭제 실패 해당 아이디가 없습니다.";
        }
        return "삭제완료! id : " + id;
    }
    //save 함수는 아이디르 전달하지 않으면 insert
    //id를 전달하면 해당 id의 대한 아이다가 있으면 update
    //id를 전달하면 해당 데이터가 없으면 인설트
    @Transactional // 더티체킹, 종료시 commit 됨
    @PutMapping("/dummy/user/{id}")
    public User UserUpdate(@PathVariable int id, @RequestBody User requestUser){ // 제이슨받기 -> 메세지컨버터작동
        System.out.println("id " + id);
        System.out.println("password " + requestUser.getPassword());
        System.out.println("email " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정실패");
        }); // 자바의 문제점. 함수를 넣어야하는데 못넣게함 ㅅㅂ 그래서 람다식
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //userRepository.save(user);
        // 원래 save는 인설트에서 쓰는 것임. 그런데 만약 ㅣ있는 값이면 업데이트해줌. 그런데 또 아에 없으면
        // 널로 바뀌어버리는 문제가 생김. 즉 업데이트시는 지양하는 것이 좋음
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user") // 한 페이지당 2건의 데이터를 받을 것
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<User> pasingUsers = userRepository.findAll(pageable);

        List<User> users = pasingUsers.getContent();
        return users;
    }

    // {id} 주소로 파라레터를 전달 받을 수 있음
    // host: / host/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){ // 상세보기
        // user/4를 찾으면 내가 데ㅣ터베이스에서 못찾으면 user가 null이 된다. 그럼 리턴시 null이 되며 이것은 버그가 된다.
        // 그러므로 옵셔널로 user객체를 감싸 가져오겠다. null인지 아닌지 판단하여라
        // 잘못된 인수 => 예외처리
        // 람다식?
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다! id : " + id); // 잘못된 인수
            }
        });
        // user 객체 = 자바 오브젝트
        // 요청 : 웹브라우저
        // 자바 오브젝트 => 웹 브라우저가 이해할 수 있는 데이터로 변환 대표적으로 JSON (예전같으면 Gson)
        // 스프링부트 = 메세지컨버터가 자동 작동
        // 만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러르를 호출하여 자동 변환해줌
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){ // key = value (약속된 규칙 x.www.from), 오브젝트도 받을 수 있음
        System.out.println("username : "+user.getUsername());
        System.out.println("password : "+user.getPassword());
        System.out.println("email : "+user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }
}
