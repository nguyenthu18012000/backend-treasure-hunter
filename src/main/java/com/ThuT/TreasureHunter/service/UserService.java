package com.ThuT.TreasureHunter.service;

import com.ThuT.TreasureHunter.pojo.dto.request.auth.RegisterRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.response.auth.LoginResponseDTO;

public interface UserService {
    void register(RegisterRequestDTO request);

    LoginResponseDTO login(String username, String password);
}
