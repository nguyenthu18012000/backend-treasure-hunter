package com.ThuT.TreasureHunter.service.implement;

import com.ThuT.TreasureHunter.pojo.dto.request.auth.RegisterRequestDTO;
import com.ThuT.TreasureHunter.pojo.entity.postgres.UserEntity;
import com.ThuT.TreasureHunter.repository.postgres.UserRepository;
import com.ThuT.TreasureHunter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequestDTO request) {

        UserEntity user = userRepository.getUserByUsername(request.getUsername());
        if (user != null) {
            throw new RuntimeException("User already exists");
        }
        userRepository.insertUser(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        );
    }
}
