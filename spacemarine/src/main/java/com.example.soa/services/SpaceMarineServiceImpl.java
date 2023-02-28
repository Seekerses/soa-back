package com.example.soa.services;

import com.example.soa.model.MeleeWeapon;
import com.example.soa.model.SpaceMarine;
import com.example.soa.model.dto.SpaceMarineCreateDTO;
import com.example.soa.model.dto.SpaceMarinePage;
import com.example.soa.model.dto.SpaceMarineUpdateDto;
import com.example.soa.services.exceptions.BadParams;
import com.example.soa.services.exceptions.EntityNotFoundException;
import com.example.soa.services.factory.ChapterFactory;
import com.example.soa.services.factory.SpaceMarineFactory;
import com.example.soa.util.NameGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SpaceMarineServiceImpl implements SpaceMarineService {

    private final SpaceMarineRepository repository;
    private final SpaceMarineFilterRepository filterRepository;
    private final SpaceMarineFactory factory;
    private final ChapterFactory chapterFactory;

    @Override
    public SpaceMarine getById(Long id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public SpaceMarine create(SpaceMarineCreateDTO createDTO) throws BadParams {
        return repository.saveAndFlush(factory.create(createDTO));
    }

    @Override
    public List<SpaceMarine> getAll() {
        return repository.findAll();
    }

    @Override
    public SpaceMarine updateById(SpaceMarineUpdateDto updateDto) throws EntityNotFoundException, BadParams {
        if ((updateDto.getName() != null && updateDto.getName().equals("")) ||
        	updateDto.getName() == null ||
                updateDto.getHealth() == null || updateDto.getHealth() <= 0 ||
                updateDto.getHeartCount() <= 0 || updateDto.getHeartCount() > 3 ||
                updateDto.getLoyal() == null || updateDto.getXCoordinate() == null ||
                updateDto.getYCoordinate() == null || updateDto.getXCoordinate() <= -282 ||
                ((updateDto.getChapterWorld() != null && !updateDto.getChapterWorld().equals("")) && (updateDto.getChapterName() == null || (updateDto.getChapterName() != null && updateDto.getChapterName().equals(""))))) throw new BadParams();
        SpaceMarine spaceMarine = repository.findById(updateDto.getId()).orElseThrow(EntityNotFoundException::new);
        spaceMarine.setName(updateDto.getName());
        spaceMarine.setHealth(updateDto.getHealth());
        spaceMarine.setHeartCount(updateDto.getHeartCount());
        if (updateDto.getXCoordinate() != null || updateDto.getYCoordinate() != null) {
            if (spaceMarine.getCoordinates() != null) {
                spaceMarine.getCoordinates().setX(updateDto.getXCoordinate());
                spaceMarine.getCoordinates().setY(updateDto.getYCoordinate());
            }
        }
        spaceMarine.setHealth(updateDto.getHealth());
        spaceMarine.setHeartCount(updateDto.getHeartCount());
        spaceMarine.setLoyal(updateDto.getLoyal());
        spaceMarine.setMeleeWeapon(updateDto.getMeleeWeapon());
        if (updateDto.getChapterName() != null || updateDto.getChapterWorld() != null){
            if(spaceMarine.getChapter() == null){
                spaceMarine.setChapter(chapterFactory.create(updateDto.getChapterName(), updateDto.getChapterWorld()));
            }
            else {
                spaceMarine.getChapter().setChapterName(updateDto.getChapterName());
                spaceMarine.getChapter().setWorld(updateDto.getChapterWorld());
            }
        }
        else spaceMarine.setChapter(null);
        return repository.saveAndFlush(spaceMarine);
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException {
        SpaceMarine spaceMarine = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(spaceMarine);
    }

    @Override
    public SpaceMarinePage getByFilter(Pageable pageable, Long id, String name,
                                       String createdBefore, String createdAfter,
                                       Long xCoordinateGreaterThan, Long xCoordinateLesserThan,
                                       Long xCoordinateEquals, Integer yCoordinateGreaterThan,
                                       Integer yCoordinateLesserThan, Integer yCoordinateEquals,
                                       Long healthGreaterThan, Long healthLesserThan,
                                       Long healthEquals, Integer heartCountGreaterThan,
                                       Integer heartCountLesserThan, Integer heartCountEquals,
                                       Boolean loyal, MeleeWeapon meleeWeapon, String chapterName,
                                       String chapterWorld) {
        return filterRepository.getByFilter(pageable, id, name, createdBefore, createdAfter,
                xCoordinateGreaterThan, xCoordinateLesserThan, xCoordinateEquals,
                yCoordinateGreaterThan, yCoordinateLesserThan, yCoordinateEquals,
                healthGreaterThan, healthLesserThan, healthEquals, heartCountGreaterThan,
                heartCountLesserThan, heartCountEquals, loyal, meleeWeapon, chapterName,
                chapterWorld);
    }

    @Override
    public SpaceMarine getWithMaxHealth() {
        return repository.getSpaceMarineWithMaxHealth();
    }

    @Override
    public List<NameGroup> groupByName() {
        return repository.groupByName();
    }
}
