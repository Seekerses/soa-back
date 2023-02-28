package com.example.soa.model.dto;

import com.example.soa.model.MeleeWeapon;
import com.example.soa.model.SpaceMarine;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpaceMarineDTO {

    private Long id;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long xCoordinate; //Значение поля должно быть больше -282, Поле не может быть null
    private Integer yCoordinate; //Поле не может быть null
    private String creationDate;
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0
    private int heartCount; //Значение поля должно быть больше 0, Максимальное значение поля: 3
    private Boolean loyal; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле может быть null
    private String chapterName; //Поле не может быть null, Строка не может быть пустой
    private String chapterWorld; //Поле может быть null

    public static SpaceMarineDTO create(SpaceMarine spaceMarine){
        return SpaceMarineDTO.builder()
                .id(spaceMarine.getId())
                .name(spaceMarine.getName())
                .xCoordinate(spaceMarine.getCoordinates().getX())
                .yCoordinate(spaceMarine.getCoordinates().getY())
                .health(spaceMarine.getHealth())
                .creationDate(spaceMarine.getCreationDate().toString())
                .heartCount(spaceMarine.getHeartCount())
                .loyal(spaceMarine.getLoyal())
                .meleeWeapon(spaceMarine.getMeleeWeapon())
                .chapterName(spaceMarine.getChapter() == null ? null : spaceMarine.getChapter().getChapterName())
                .chapterWorld(spaceMarine.getChapter() == null ? null : spaceMarine.getChapter().getWorld())
                .build();
    }
}
