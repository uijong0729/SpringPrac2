package com.example.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    /**
     * 서버를 기동시키면 비밀번호를 따로 지정하지 않은경우 콘솔에 출력되는 아래와 같은 메시지로 인증을 수행
     * Using generated security password: 246ba517-0dde-434d-b32c-1bf6ae79a585
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("apply configure");
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/users/**").permitAll();
        http.headers().frameOptions().disable();
    }

    /**
     * 빈 등록
     *  - 사용 : UserServiceImpl
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
