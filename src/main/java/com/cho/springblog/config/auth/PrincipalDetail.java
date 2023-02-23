package com.cho.springblog.config.auth;

import com.cho.springblog.model.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
@Data// 스프링 시큐리티가 가로채고 완료되면 유저 디테일 오브젝트가 시큐리티 세션에 저장소에 저장이된다.
public class PrincipalDetail implements UserDetails { // 추상메소드들을 모두 오버라이딩 해야함
    private User user; // 콤포지션, 객체를 품고있음

    public PrincipalDetail(User user){
        this.user = user;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { // 계정 만료
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 잠겨있는지
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 계정의 권한 목록을 리턴
        Collection<GrantedAuthority> collecters = new ArrayList<>();
        collecters.add(()->{
            return "ROLE_"+user.getRole();
        });
        return collecters;
    }
}
