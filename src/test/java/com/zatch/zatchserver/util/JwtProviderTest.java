package com.zatch.zatchserver.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class JwtProviderTest {
    @Autowired
    JwtProvider jwtProvider;

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Test
    public void jwt생성후_복호화() throws Exception {
        //given
        String accessToken = jwtProvider.createToken(1L);
        //when
        Long parsedId = jwtProvider.parse(accessToken);
        //then
        assertThat(parsedId).isEqualTo(1L);
    }

    @Test
    public void jwt_만료기간_지난_경우() throws Exception {
        Long userId = 1L;
        String accessToken = Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (1 * 1000)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        Thread.sleep(1500);

        assertThrows(ExpiredJwtException.class, ()-> jwtProvider.validate(accessToken));
    }
}