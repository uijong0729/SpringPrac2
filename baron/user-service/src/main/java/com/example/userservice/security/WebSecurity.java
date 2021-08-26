package com.example.userservice.security;

import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private Environment environment;
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurity(Environment environment, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.environment = environment;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * 서버를 기동시키면 비밀번호를 따로 지정하지 않은경우 콘솔에 출력되는 아래와 같은 메시지로 인증을 수행
     * Using generated security password: 246ba517-0dde-434d-b32c-1bf6ae79a585
     *
     * 권한부여에 관한 메소드
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("apply configure");
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();
        // IP는 ping -4 [컴퓨터 이름] 으로 조사
        http.authorizeRequests().antMatchers("/**")
                .access("hasIpAddress('192.168.11.6') or hasIpAddress('127.0.0.1') or hasIpAddress('fe80::bc7e:cbcb:8fe6:50d2%8')")
//                .hasIpAddress("fe80::bc7e:cbcb:8fe6:50d2%8")
                .and()
                .addFilter(getAuthenticationFilter());

        http.headers().frameOptions().disable();
    }

    /**
     * 인증에 관련된 작업
     *
     * 데이터베이스의 패스워드는 암호화 되어있음
     * 사용자의 요청은 암호화 되어있지 않음
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    private Filter getAuthenticationFilter() throws Exception{
        AuthenticationFilter authenticationFilter
                = new AuthenticationFilter(authenticationManager(), userService, environment);
//        authenticationFilter.setAuthenticationManager(authenticationManager());
        return authenticationFilter;
    }

}
