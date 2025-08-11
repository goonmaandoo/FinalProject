package com.example.start01.config;

import com.example.start01.utils.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 매 요청마다 딱 1번 실행되는 필터
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // 이 요청 유효한 토큰 갖고있는지 검사하는 문지기역할

    private final JwtTokenProvider jwtTokenProvider;

    // 생성자에서 JwtTokenProvider 받아옴
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 필터의 핵심: 요청이 오면 실행됨
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // ✅ 웹소켓 엔드포인트 요청은 필터링하지 않고 바로 통과
        // SockJS는 "/ws-chat/..." 와 같은 경로로 요청이 옴
        if (request.getRequestURI().startsWith("/ws-chat")) {
            System.out.println("웹소켓 엔드포인트 요청! 필터 통과.");
            filterChain.doFilter(request, response);
            return;
        }

        // 1. 요청 헤더에서 토큰 꺼냄
        String token = resolveToken(request);

        // 2. 토큰이 있고, 유효한 경우는 통과
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰 유효 → 통과
            filterChain.doFilter(request, response);
        }
        // 3. 토큰이 있는데 유효하지 않음
        else if (token != null) {
            // 유효하지 않은 토큰 → 401 오류 리턴
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("토큰이 유효하지 않거나 만료되었습니다.");
        }
        // 4. 토큰이 아예 없음 → 그냥 통과시킴 (필요한 곳만 막을 거라서)
        else {
            filterChain.doFilter(request, response);
        }
    }

    // 헤더에서 토큰 꺼내는 함수
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        // 예: "Bearer eyJhbGciOiJIUzI1NiIs..."
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 제거하고 토큰만 반환
        }

        return null;
    }
}