package com.example.userservice.security;

import com.example.userservice.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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

/**
 * 1. AuthenticationFilter -> attemptAuthentication()
 * 2. UsernamePasswordAuthenticationToken
 * 3. UserDetailService
 * 4. UserRepository
 * 5. AuthenticationFilter -> (로그인 성공 시) successfulAuthentication -> findUserByEmail() -> add header JTW token
 */
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

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
    }
}
