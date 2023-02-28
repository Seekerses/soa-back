package com.example.soa.services.factory;

import com.example.soa.model.SpaceMarine;
import com.example.soa.model.dto.SpaceMarineCreateDTO;
import com.example.soa.services.exceptions.BadParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Service
public class SpaceMarineFactoryImpl implements SpaceMarineFactory {

    private final CoordinatesFactory coordinatesFactory;
    private final ChapterFactory chapterFactory;
    @Override
    public SpaceMarine create(SpaceMarineCreateDTO createDTO) throws BadParams {
        if (createDTO.getName() == null || createDTO.getName().equals("") ||
        createDTO.getHealth() == null || createDTO.getHealth() <= 0 ||
        createDTO.getHeartCount() == null || createDTO.getHeartCount() <= 0 || createDTO.getHeartCount() > 3 ||
        createDTO.getLoyal() == null) throw new BadParams();
        return SpaceMarine.builder()
                .name(createDTO.getName())
                .coordinates(coordinatesFactory.create(createDTO.getXCoordinate(), createDTO.getYCoordinate()))
                .creationDate(ZonedDateTime.now())
                .health(createDTO.getHealth())
                .heartCount(createDTO.getHeartCount())
                .loyal(createDTO.getLoyal())
                .meleeWeapon(createDTO.getMeleeWeapon())
                .chapter(createDTO.getChapterName() == null ? null : chapterFactory.create(createDTO.getChapterName(), createDTO.getChapterWorld()))
                .build();
    }
}
