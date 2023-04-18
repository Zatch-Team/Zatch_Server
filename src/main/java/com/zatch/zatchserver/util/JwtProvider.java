package com.zatch.zatchserver.util;

import com.zatch.zatchserver.dto.JwtRefreshToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final long ACCESS_TOKEN_EXPIRATION = 365 * 24 * 60 * 60 * 1000; // 365일
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000L;

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    // access token
    public String createToken(Long userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION);

        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // refresh token
    public JwtRefreshToken createRefreshToken(Long userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION);

        String token = Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return new JwtRefreshToken(token);
    }

    // token에서 userId 추출하기
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
