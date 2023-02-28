package com.example.soa.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class SpaceMarinePageDTO {
    private List<SpaceMarineDTO> list;
    private Long count;

    public static SpaceMarinePageDTO create(SpaceMarinePage page){
        return SpaceMarinePageDTO.builder()
                .list(page.getData().stream().map(SpaceMarineDTO::create).collect(Collectors.toList()))
                .count(page.getCount())
                .build();
    }
}
