package com.example.soa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Chapter {
    @NotNull
    @NotBlank
    private String chapterName; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private String world; //Поле может быть null
}
