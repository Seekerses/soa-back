package com.example.soa.model.dto;

import com.example.soa.model.SpaceMarine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class SpaceMarinePage {
    private List<SpaceMarine> data;
    private Long count;
}
