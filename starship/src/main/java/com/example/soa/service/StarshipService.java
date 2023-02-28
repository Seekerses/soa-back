package com.example.soa.service;

import com.example.soa.model.dto.Starship;
import com.example.soa.service.exceptions.EntityNotFoundException;
import com.example.soa.service.exceptions.SpaceMarineAlreadyLoaded;
import com.example.soa.service.exceptions.SpaceMarineDoesNotExist;

import java.util.List;

public interface StarshipService {
    void loadSpaceMarine(Long shipId, Long marineId) throws EntityNotFoundException, SpaceMarineAlreadyLoaded, SpaceMarineDoesNotExist;
    void unloadAll(Long id) throws EntityNotFoundException;
    void create();

    void delete(Long shipId) throws EntityNotFoundException;

    List<Starship> getAll();
}
