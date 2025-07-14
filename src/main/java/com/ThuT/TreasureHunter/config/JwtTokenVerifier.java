package com.ThuT.TreasureHunter.config;

import com.ThuT.TreasureHunter.constant.CommonConstant;
import com.ThuT.TreasureHunter.pojo.entity.postgres.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        log.info("JwtTokenVerifier is running for path: {}", request.getRequestURI());
        try {
            String authorizationHeader = request.getHeader(CommonConstant.AUTHORIZATION_HEADER);
            if (Strings.isEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
                request.setAttribute(CommonConstant.AUTH_ERROR_MESSAGE, "Missing Token!");
                filterChain.doFilter(request, response);
                return;
            }

            String token = authorizationHeader.replace("Bearer ", "");
            UserEntity userLogin = jwtUtil.validateToken(token);
            if (userLogin == null) {
//                throw new CustomException("Invalid token");
                return;
            }
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userLogin.getId(), null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            log.error("Authentication failed: {} - {}", ex.getMessage(), request.getRequestURI());
            log.debug("Authentication failed: ", ex);
        }

        filterChain.doFilter(request, response);
    }
}
