package com.zatch.zatchserver.config;

import com.zatch.zatchserver.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Slf4j
//@RequiredArgsConstructor
//@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final String AUTHORIZATION_PREFIX = "Bearer ";

//    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!needToCheckJwt(request)) {
            filterChain.doFilter(request,response);
            return;
        }
        // Request Header에 accessToken(jwt)가 존재하지 않는 경우
        if (!jwtExists(request)) {

            // 예외
        }
//        String accessToken = getJwt(request);
//
//        // accessToken이 유효하지 않을 경우
//        if (!jwtProvider.validate(accessToken)) {
//            //예외
//        }
//
//        addUserIdToRequest(jwtProvider.parse(accessToken), request);
//        filterChain.doFilter(request, response);


    }

    public boolean needToCheckJwt(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
//        log.info(requestUrl);
        if (requestUrl.startsWith("/users/new") || requestUrl.startsWith("/users/login")) {
            return false;
        }

        if (requestUrl.startsWith("/users") || requestUrl.startsWith("/zatch")) {
            return true;
        }

        return false;
    }

    // HttpServletRequest 객체에 복호화된 userId를 attribute로 등록
    private void addUserIdToRequest(Long userId, HttpServletRequest request) {
        request.setAttribute("userID", userId);
    }


    public boolean jwtExists(HttpServletRequest request) {
        return request.getHeader("Authorization") != null;
    }

    private String getJwt(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader.substring(7);
    }
}