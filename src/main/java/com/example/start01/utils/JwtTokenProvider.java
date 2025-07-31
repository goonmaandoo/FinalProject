package com.example.start01.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds; // 밀리초 단위

    @PostConstruct
    protected void init() {
        // secretKey를 Base64로 인코딩해서 안전하게 관리
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT 토큰 생성 (예: 로그인 성공 후 email로 토큰 생성)
    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)      // payload에 이메일 넣음
                .setIssuedAt(now)        // 발행일
                .setExpiration(validity) // 만료일
                .signWith(SignatureAlgorithm.HS256, secretKey) // HS256 알고리즘과 비밀키로 서명
                .compact();
    }

    // JWT 토큰에서 email 가져오기
    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // JWT 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // 토큰이 잘못됐거나 만료됨
            return false;
        }
    }
}
