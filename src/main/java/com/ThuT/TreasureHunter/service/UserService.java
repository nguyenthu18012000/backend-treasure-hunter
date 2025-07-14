package com.ThuT.TreasureHunter.service;

import com.ThuT.TreasureHunter.pojo.dto.request.auth.RegisterRequestDTO;

public interface UserService {
    void register(RegisterRequestDTO request);
}
