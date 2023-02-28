package com.example.soa.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class NameGroup {

    @NotNull
    private String name;
    private Long count;
}
