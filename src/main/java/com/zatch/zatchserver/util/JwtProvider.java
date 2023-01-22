package com.zatch.zatchserver.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    private final Integer ACCESS_TOKEN_EXPIRATION = 365 * 24 * 60 * 60 * 1000; // 365일

    public String createToken(Long userId) {
        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Long parse(String jwt) {
        return Long.parseLong(
                Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(jwt)
                        .getBody()
                        .get("userId")
                        .toString()
        );
    }

    // 유효성 체크, jwt토큰의 만료시간이 지나지 않았는지로 판단
    public Boolean validate(String jwt) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody()
                .getExpiration()
                .after(new Date());
    }
}
