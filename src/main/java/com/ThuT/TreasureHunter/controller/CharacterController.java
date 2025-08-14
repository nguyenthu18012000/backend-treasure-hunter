package com.ThuT.TreasureHunter.controller;

import com.ThuT.TreasureHunter.constant.CommonConstant;
import com.ThuT.TreasureHunter.pojo.dto.request.character.CreateCharacterRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.response.character.DetailCharacterResponseDTO;
import com.ThuT.TreasureHunter.response.BaseResponse;
import com.ThuT.TreasureHunter.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Void>> createCharacter(@RequestBody CreateCharacterRequestDTO request) {
        characterService.createCharacter(request);
        BaseResponse<Void> response = new BaseResponse<>(
                CommonConstant.RESPONSE_CODE.SUCCESS,
                CommonConstant.RESPONSE_MESSAGE.SUCCESS,
                null
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<DetailCharacterResponseDTO>> getCharacterDetail() {
        DetailCharacterResponseDTO detailCharacter = characterService.getCharacterDetail();
        BaseResponse<DetailCharacterResponseDTO> response = new BaseResponse<>(
                CommonConstant.RESPONSE_CODE.SUCCESS,
                CommonConstant.RESPONSE_MESSAGE.SUCCESS,
                detailCharacter
        );
        return ResponseEntity.ok(response);
    }
}
