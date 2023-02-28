package com.example.soa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class SpaceMarine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotNull
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    @Embedded
    private Coordinates coordinates; //Поле не может быть null
    @NotNull
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @NotNull
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0
    private int heartCount; //Значение поля должно быть больше 0, Максимальное значение поля: 3
    @NotNull
    private Boolean loyal; //Поле не может быть null
    @NotNull
    @Enumerated(EnumType.STRING)
    private MeleeWeapon meleeWeapon; //Поле может быть null
    @NotNull
    @Embedded
    private Chapter chapter; //Поле может быть null
}
