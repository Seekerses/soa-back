package com.example.soa.services;

import com.example.soa.model.MeleeWeapon;
import com.example.soa.model.SpaceMarine;
import com.example.soa.model.dto.SpaceMarineCreateDTO;
import com.example.soa.model.dto.SpaceMarinePage;
import com.example.soa.model.dto.SpaceMarineUpdateDto;
import com.example.soa.services.exceptions.BadParams;
import com.example.soa.services.exceptions.EntityNotFoundException;
import com.example.soa.util.NameGroup;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpaceMarineService {
    SpaceMarine getById(Long id) throws EntityNotFoundException;
    SpaceMarine create(SpaceMarineCreateDTO createDTO) throws BadParams;
    List<SpaceMarine> getAll();
    SpaceMarine updateById(SpaceMarineUpdateDto updateDto) throws EntityNotFoundException, BadParams;
    void deleteById(Long id) throws EntityNotFoundException;
    SpaceMarinePage getByFilter(Pageable pageable,
                                Long id,
                                String name,
                                String createdBefore,
                                String createdAfter,
                                Long xCoordinateGreaterThan,
                                Long xCoordinateLesserThan,
                                Long xCoordinateEquals,
                                Integer yCoordinateGreaterThan,
                                Integer yCoordinateLesserThan,
                                Integer yCoordinateEquals,
                                Long healthGreaterThan,
                                Long healthLesserThan,
                                Long healthEquals,
                                Integer heartCountGreaterThan,
                                Integer heartCountLesserThan,
                                Integer heartCountEquals,
                                Boolean loyal,
                                MeleeWeapon meleeWeapon,
                                String chapterName,
                                String chapterWorld);
    SpaceMarine getWithMaxHealth();
    List<NameGroup> groupByName();
}
