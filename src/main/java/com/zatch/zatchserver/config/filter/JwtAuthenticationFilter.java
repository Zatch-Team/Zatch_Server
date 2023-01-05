package com.zatch.zatchserver.config.filter;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestUri = httpRequest.getRequestURI();
        logger.info("Requset URI : " + requestUri);

        if (isRequestForPersonal(requestUri)) {
            String jwtToken = getToken(httpRequest.getHeader(AUTHORIZATION_HEADER));
            logger.info(jwtToken);
        }

        chain.doFilter(request, response);
    }

    // requset header에 'Authorization : Bearer jwttoken' 형식으로 들어있는데, jwttoken값 추출
    private String getToken(String authorization) {
        if (authorization.startsWith(BEARER_PREFIX)) {
            return authorization.substring(7);
        }

        return null;
    }

    // 요청 uri가 jwt 검사가 필요한지 check
    private boolean isRequestForPersonal(String requestUri) {
        return true;
    }
}
