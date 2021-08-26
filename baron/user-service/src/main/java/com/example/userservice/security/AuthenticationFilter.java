package com.example.userservice.security;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * 1. AuthenticationFilter -> attemptAuthentication()
 * 2. UsernamePasswordAuthenticationToken
 * 3. UserDetailService
 * 4. UserRepository
 * 5. AuthenticationFilter -> (로그인 성공 시) successfulAuthentication -> findUserByEmail() -> add header JTW token
 */
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment env) {
        super(authenticationManager);
        this.userService = userService;
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            // Json파일 -> 클래스에 맵핑
            RequestLogin credential = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            // 토큰 생성
            // authenticate : 진짜임을 증명하다
            // credential : 자격을 수여하다
            return getAuthenticationManager().authenticate(
                    /**
                     * JWT 로그인 토큰은 Email과 Password로 만든다.
                     * {@link com.example.userservice.service.UserServiceImpl#loadUserByUsername(String)}
                     */
                    new UsernamePasswordAuthenticationToken(
                            credential.getEmail(),
                            credential.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * 1. SecurityContextHolder で成功した Authentication オブジェクトを設定する
     * 2. ログインの成功を構成済みの RememberMeServices に通知する
     * 3. 構成された ApplicationEventPublisher を介して InteractiveAuthenticationSuccessEvent を起動する
     * 4. 追加の動作を AuthenticationSuccessHandler に委譲します。
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        // 로그인에 성공해도 아무것도 하지 않는다.
        // super.successfulAuthentication(request, response, chain, authResult);
        log.info(
                authResult.getPrincipal().toString()
        );
        log.info(
                ((User)authResult.getPrincipal()).getUsername()
        );

        String userName = ((User)authResult.getPrincipal()).getUsername();
        UserDto userDetails = userService.getUserDetailsByEmail(userName);

        // JWT 토큰
        // ■관련 프로퍼티
        //        token:
        //          expiration_time: 86400000
        //          secret: user_token
        // ■compact : 계약을 맺다
        String token = Jwts.builder()
                .setSubject(userDetails.getUserId())
                .setExpiration(new Date(
                        System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))
                ))
                // 조합 키 지정 (암호화 할 문자열에 대해 더미 문자열을 덧대고 암호화를 수행)
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        // 위에서 만든 토큰은 헤더에서 확인 가능
        // 토큰은 세션이나 쿠키를 대신해서 클라이언트가 동일한 사람이라는 것을 증명하는 데 쓰인다.
        // 토큰을 이용하면 인증이 다른 서비스 간 인증정보를 공유할 수 있다.
        // 백엔드와 프론트엔드의 개발영역을 나누더라도 인증은 토큰을 확인하면 된다.
        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getUserId());

    }
}
