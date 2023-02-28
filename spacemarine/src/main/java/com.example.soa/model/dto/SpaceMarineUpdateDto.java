package com.example.soa.model.dto;

import com.example.soa.model.MeleeWeapon;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SpaceMarineUpdateDto {

    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    private Long xCoordinate; //Значение поля должно быть больше -282, Поле не может быть null

    @NotNull
    private Integer yCoordinate; //Поле не может быть null

    @NotNull
    @Min(0)
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0

    @Min(0)
    @Max(3)
    private int heartCount; //Значение поля должно быть больше 0, Максимальное значение поля: 3

    @NotNull
    private Boolean loyal; //Поле не может быть null

    @NotNull
    private MeleeWeapon meleeWeapon; //Поле может быть null

    @NotNull
    @NotEmpty
    private String chapterName; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    private String chapterWorld; //Поле может быть null
}
