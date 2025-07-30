package com.ThuT.TreasureHunter.service.implement;

import com.ThuT.TreasureHunter.config.JwtUtil;
import com.ThuT.TreasureHunter.exception.CommonException;
import com.ThuT.TreasureHunter.pojo.dto.request.auth.RegisterRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.response.auth.LoginResponseDTO;
import com.ThuT.TreasureHunter.pojo.entity.postgres.UserEntity;
import com.ThuT.TreasureHunter.repository.postgres.UserRepository;
import com.ThuT.TreasureHunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequestDTO request) {

        UserEntity user = userRepository.getUserByUsername(request.getUsername());
        if (user != null) {
            throw new CommonException("100", "User already exists");
        }
        userRepository.insertUser(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        );
    }

    @Override
    public LoginResponseDTO login(String username, String password) {
        UserEntity user = userRepository.getUserByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new CommonException("100", "Invalid username or password");
        }

        LoginResponseDTO response = new LoginResponseDTO();

        response.setAccessToken(jwtUtil.generateLoginJwtToken(user));

        return response;
    }
}
