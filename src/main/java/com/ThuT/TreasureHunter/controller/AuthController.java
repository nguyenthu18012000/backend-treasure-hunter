package com.ThuT.TreasureHunter.controller;

import com.ThuT.TreasureHunter.pojo.dto.request.auth.RegisterRequestDTO;
import com.ThuT.TreasureHunter.response.BaseResponse;
import com.ThuT.TreasureHunter.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<Void>> register(
            @Valid() @RequestBody() RegisterRequestDTO request
    ) {
        authService.register(request);
        return null;
    }
}
