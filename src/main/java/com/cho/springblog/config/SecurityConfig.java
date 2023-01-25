package com.cho.springblog.config;

import com.cho.springblog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있는 것
@Configuration // 빈등록
@EnableWebSecurity // 필터추가 . 이 녀석이 모든 request를 가로챔 스프링 시큐리티가 활성화 되어 있는데 어떤 설정을 해당파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true)// 미리체크하겠다. 위의 세개는 세트
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PrincipalDetailService principalDetailService;
    @Bean // IoC가 된다. 함수가 리턴하는 값을 스프링이 관리한다.
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    //시큐리티가 대신 로그인해줄때 패스워드를 가로채는데 해당 패스워드가 뭘로 해쉬가 되어 회원가입 되었는지 알아야 같은 해쉬로 암호화해서 DB에 있는 해쉬와 비교가능


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable(). // csfr 토큰 비활성화 테스트시 걸어두는게 좋음
                authorizeHttpRequests().
                antMatchers("/auth/**", "/js/**", "/css/**", "/image/**", "/").
                permitAll().
                anyRequest().
                authenticated()
                .and().
                formLogin().
                loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/"); // auth빼고 전부 인증 필요해, 인증필요하면 이곳으로. 시큐리티가 가로채서 로그인
    }
}
