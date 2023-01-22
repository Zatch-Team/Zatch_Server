package com.zatch.zatchserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
// 메모리 내에서 세션 관리하는 세션 관리자
public class SessionManager {
    private Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    public void createSession(Long userId, HttpServletResponse response) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, userId);
        log.info("new session : {} at {}",sessionId, LocalDateTime.now());

        Cookie sessionCookie = new Cookie("zatch-session", sessionId);
        // 쿠키 생명주기 60 * 60초
        sessionCookie.setMaxAge(60 * 60);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true);
        response.addCookie(sessionCookie);

        log.info("num of sessions : " + sessionStore.size());
    }
}
