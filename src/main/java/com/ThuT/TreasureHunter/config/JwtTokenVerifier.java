package com.ThuT.TreasureHunter.config;

import com.ThuT.TreasureHunter.constant.CommonConstant;
import com.ThuT.TreasureHunter.exception.CommonException;
import com.ThuT.TreasureHunter.pojo.entity.postgres.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws IOException, ServletException {
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
                throw new CommonException("401", "Invalid token");
            }
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userLogin, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            log.error("Authentication failed: {} - {}", ex.getMessage(), request.getRequestURI());
            log.debug("Authentication failed: ", ex);
        }

        filterChain.doFilter(request, response);
    }
}
