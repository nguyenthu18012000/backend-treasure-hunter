package com.ThuT.TreasureHunter.service;

import com.ThuT.TreasureHunter.pojo.dto.request.auth.LoginRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.request.auth.RegisterRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.response.auth.LoginResponseDTO;

public interface AuthService {
    void register(RegisterRequestDTO request);

    LoginResponseDTO login(LoginRequestDTO request);

    Long getUserId();
}
