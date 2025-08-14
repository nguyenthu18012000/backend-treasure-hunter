package com.ThuT.TreasureHunter.service.implement;

import com.ThuT.TreasureHunter.exception.CommonException;
import com.ThuT.TreasureHunter.pojo.dto.request.auth.LoginRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.request.auth.RegisterRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.response.auth.LoginResponseDTO;
import com.ThuT.TreasureHunter.pojo.entity.postgres.UserEntity;
import com.ThuT.TreasureHunter.service.AuthService;
import com.ThuT.TreasureHunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;

    @Override
    public void register(RegisterRequestDTO request) {
        userService.register(request);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        return userService.login(request.getUsername(), request.getPassword());
    }

    @Override
    public Long getUserId() {
        UserEntity user = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserEntity u) {
            user = u;
        }

        if (user == null) {
            throw new CommonException("401", "Invalid user");
        }

        return user.getId();
    }
}
