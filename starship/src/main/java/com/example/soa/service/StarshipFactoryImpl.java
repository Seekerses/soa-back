package com.example.soa.service;

import com.example.soa.model.dto.Starship;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class StarshipFactoryImpl implements StarshipFactory {
    @Override
    public Starship create() {
        return Starship.builder()
                .spaceMarines(new HashSet<>())
                .build();
    }
}
