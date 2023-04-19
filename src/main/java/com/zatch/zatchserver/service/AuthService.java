package com.zatch.zatchserver.service;

import com.zatch.zatchserver.dto.JwtRefreshToken;
import com.zatch.zatchserver.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final JwtProvider jwtProvider;
    private final JdbcTemplate jdbcTemplate;

    public String issueAccessToken(Long userId) {
        return jwtProvider.createToken(userId);
    }
    public String  issueRefreshToken(Long userId) {
        String refresh_token = String.valueOf(jwtProvider.createRefreshToken(userId));
        System.out.println("refresh token : " + refresh_token);
        try {
            String sql = "UPDATE user SET refresh_token = ? WHERE user_id = ?";
            Object[] params = {refresh_token, userId};
            jdbcTemplate.update(sql, params);
            System.out.println("Update Refresh Token sql update");
            return refresh_token;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Refresh Token Update Error");
        }
    }
}
