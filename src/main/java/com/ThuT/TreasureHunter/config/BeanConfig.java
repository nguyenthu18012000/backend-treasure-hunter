package com.ThuT.TreasureHunter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public JwtTokenVerifier jwtTokenVerifier() {
        return new JwtTokenVerifier(jwtUtil);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
