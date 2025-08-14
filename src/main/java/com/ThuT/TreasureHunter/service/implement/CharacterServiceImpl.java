package com.ThuT.TreasureHunter.service.implement;

import com.ThuT.TreasureHunter.pojo.dto.request.character.CreateCharacterRequestDTO;
import com.ThuT.TreasureHunter.pojo.dto.response.character.DetailCharacterResponseDTO;
import com.ThuT.TreasureHunter.pojo.entity.postgres.CharacterEntity;
import com.ThuT.TreasureHunter.repository.postgres.CharacterRepository;
import com.ThuT.TreasureHunter.service.AuthService;
import com.ThuT.TreasureHunter.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    private final AuthService authService;

    @Override
    public void createCharacter(CreateCharacterRequestDTO request) {
        Long userId = authService.getUserId();
        characterRepository.insertCharacter(request.getCharacterName(), userId);
    }

    @Override
    public DetailCharacterResponseDTO getCharacterDetail() {
        Long userId = authService.getUserId();

        CharacterEntity character = characterRepository.getCharacterDetail(userId);
        if (character == null) {
            return null;
        }

        DetailCharacterResponseDTO response = new DetailCharacterResponseDTO();
        BeanUtils.copyProperties(character, response);

        return response;
    }
}
