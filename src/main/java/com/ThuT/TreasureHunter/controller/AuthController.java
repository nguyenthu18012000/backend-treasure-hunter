package com.ThuT.TreasureHunter.controller;

import com.ThuT.TreasureHunter.constant.CommonConstant;
import com.ThuT.TreasureHunter.pojo.dto.request.auth.LoginRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.request.auth.RegisterRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.response.auth.LoginResponseDTO;
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
        BaseResponse<Void> response = new BaseResponse<>(
                CommonConstant.RESPONSE_CODE.SUCCESS,
                CommonConstant.RESPONSE_MESSAGE.SUCCESS,
                null
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponseDTO>> login(
            @Valid() @RequestBody() LoginRequestDTO request
    ) {
        LoginResponseDTO loginResponse = authService.login(request);
        BaseResponse<LoginResponseDTO> response = new BaseResponse<>(
                CommonConstant.RESPONSE_CODE.SUCCESS,
                CommonConstant.RESPONSE_MESSAGE.SUCCESS,
                loginResponse
        );
        return ResponseEntity.ok(response);
    }
}
