package com.zatch.zatchserver.config;

import com.zatch.zatchserver.service.AuthService;
import com.zatch.zatchserver.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final String AUTHORIZATION_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!needToCheckJwt(request)) {
            System.out.println("JWT"+"예외 0");

            filterChain.doFilter(request,response);
            return;
        }
        // Request Header에 accessToken(jwt)가 존재하지 않는 경우
        if (!jwtExists(request)) {

            // 예외
            System.out.println("JWT"+"예외1");
        }
        String accessToken = getJwt(request);
        System.out.println("JWT"+"doFilterInternal validate" + jwtProvider.validate(accessToken));

        System.out.println("JWT"+"Authorization" + jwtProvider);
        // accessToken이 유효하지 않을 경우
        if (!jwtProvider.validate(accessToken)) {
            //예외
            System.out.println("JWT"+"예외2");
        }

        addUserIdToRequest(jwtProvider.parse(accessToken), request);
        filterChain.doFilter(request, response);



    }

    public boolean needToCheckJwt(HttpServletRequest request) {

        System.out.println("JWT"+"needToCheckJwt request = "+ request);
        String requestUrl = request.getRequestURI();
        log.info(requestUrl);
        if (requestUrl.startsWith("/users/new")) { //로그인 삭제
            System.out.println("JWT"+"회원가입");

            return false;
        }
        System.out.println("JWT"+"needToCheckJwt"+requestUrl.startsWith("/users"));

        if (requestUrl.startsWith("/users") || requestUrl.startsWith("/zatch")) {
            System.out.println("JWT"+"그냥 users");

            return true;

        }

        return false;
    }

    // HttpServletRequest 객체에 복호화된 userId를 attribute로 등록
    private void addUserIdToRequest(Long userId, HttpServletRequest request) {
        System.out.println("JWT"+"addUserIdToRequest");

        request.setAttribute("userID", userId);
    }


    public boolean jwtExists(HttpServletRequest request) {
        System.out.println("JWT"+"jwtExists + "+ request.getHeader("Authorization"));

        return request.getHeader("Authorization") != null;
    }

    private String getJwt(HttpServletRequest request) {

        System.out.println("JWT"+"getJwt");
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("JWT"+"authorizationHeader >> "+ authorizationHeader);

        return authorizationHeader.substring(7);
    }
}