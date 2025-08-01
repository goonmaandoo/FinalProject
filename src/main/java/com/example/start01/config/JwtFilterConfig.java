package com.example.start01.config;

import com.example.start01.utils.JwtTokenProvider;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 클래스임을 Spring에게 알림
public class JwtFilterConfig {
    // JwtAuthenticationFilter가 문지기면 여기서는 그 문지기를 어디에 세울지 정하는 설정자

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public FilterRegistrationBean<Filter> jwtFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        // 필터 등록
        registrationBean.setFilter(new JwtAuthenticationFilter(jwtTokenProvider));

        // 이 경로들에만 필터 적용 ex) 로그인 없이 접근 못 하게
        registrationBean.addUrlPatterns("/api/secure/*");

        return registrationBean;
    }
}
