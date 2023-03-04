package com.zatch.zatchserver.service;

import com.zatch.zatchserver.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final JwtProvider jwtProvider;

    public String issueAccessToken(Long userId) {
        return jwtProvider.createToken(userId);
    }
}
