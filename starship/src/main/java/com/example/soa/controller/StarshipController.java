package com.example.soa.controller;

import com.example.soa.model.dto.Starship;
import com.example.soa.service.StarshipService;
import com.example.soa.service.exceptions.EntityNotFoundException;
import com.example.soa.service.exceptions.SpaceMarineAlreadyLoaded;
import com.example.soa.service.exceptions.SpaceMarineDoesNotExist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/starship")
public class StarshipController {

    private final StarshipService service;

    @PostMapping("/{starship-id}/load/{space-marine-id}")
    public ResponseEntity<Void> loadSpaceMarineOnShip(@PathVariable("starship-id") Long starshipId, @PathVariable("space-marine-id") Long spaceMarineId) throws SpaceMarineAlreadyLoaded, EntityNotFoundException, SpaceMarineDoesNotExist {
        service.loadSpaceMarine(starshipId, spaceMarineId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{starship-id}/unload-all")
    public ResponseEntity<Void> unloadStarship(@PathVariable("starship-id") Long starshipId) throws EntityNotFoundException {
        service.unloadAll(starshipId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(){
        service.create();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{starshipId}")
    public ResponseEntity<Void> delete(@PathVariable Long starshipId) throws EntityNotFoundException {
        service.delete(starshipId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Starship>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}