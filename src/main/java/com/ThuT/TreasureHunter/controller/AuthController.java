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
import org.springframework.web.bind.annotation.*;

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

//    // test
//    @GetMapping("/userinfo")
//    public ResponseEntity<?> getUserInfoFromToken() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() instanceof UserEntity user) {
//            Long userId = user.getId();
//            return ResponseEntity.ok("User ID: " + userId);
//        }
//        return ResponseEntity.status(401).body("Unauthorized");
//    }
}
