package com.example.soa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Coordinates {
    @NotNull
    private Long x; //Значение поля должно быть больше -282, Поле не может быть null
    @NotNull
    private Integer y; //Поле не может быть null
}
