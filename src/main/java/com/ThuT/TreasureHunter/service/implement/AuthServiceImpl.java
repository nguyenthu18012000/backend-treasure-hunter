package com.ThuT.TreasureHunter.service.implement;

import com.ThuT.TreasureHunter.pojo.dto.request.auth.RegisterRequestDTO;
import com.ThuT.TreasureHunter.service.AuthService;
import com.ThuT.TreasureHunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;

    @Override
    public void register(RegisterRequestDTO request) {
        userService.register(request);
    }
}
