package com.ThuT.TreasureHunter.service;

import com.ThuT.TreasureHunter.pojo.dto.request.auth.RegisterRequestDTO;

public interface AuthService {
    void register(RegisterRequestDTO request);
}
