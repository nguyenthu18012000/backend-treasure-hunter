package com.ThuT.TreasureHunter.service;

import com.ThuT.TreasureHunter.pojo.dto.request.character.CreateCharacterRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.response.character.DetailCharacterResponseDTO;

public interface CharacterService {
    void createCharacter(CreateCharacterRequestDTO request);

    DetailCharacterResponseDTO getCharacterDetail();
}
