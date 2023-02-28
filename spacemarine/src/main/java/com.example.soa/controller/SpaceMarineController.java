package com.example.soa.controller;

import com.example.soa.model.MeleeWeapon;
import com.example.soa.model.dto.SpaceMarineCreateDTO;
import com.example.soa.model.dto.SpaceMarineDTO;
import com.example.soa.model.dto.SpaceMarinePageDTO;
import com.example.soa.model.dto.SpaceMarineUpdateDto;
import com.example.soa.services.SpaceMarineService;
import com.example.soa.services.exceptions.BadParams;
import com.example.soa.services.exceptions.EntityNotFoundException;
import com.example.soa.util.NameGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/space-marines")
public class SpaceMarineController {

    private final SpaceMarineService service;

    @PostMapping("/")
    public ResponseEntity<SpaceMarineDTO> create(@RequestBody SpaceMarineCreateDTO createDTO) throws BadParams {
        log.info(createDTO);
        return ResponseEntity.ok(SpaceMarineDTO.create(service.create(createDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceMarineDTO> getById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(SpaceMarineDTO.create(service.getById(id)));
    }

    @GetMapping("/")
    public ResponseEntity<List<SpaceMarineDTO>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(SpaceMarineDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/by-filter")
    public ResponseEntity<SpaceMarinePageDTO> getByFilter(Pageable pageable,
                                                            @RequestParam(required = false) Long id,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String createdBefore,
                                                            @RequestParam(required = false) String createdAfter,
                                                            @RequestParam(required = false) Long xCoordinateGreaterThan,
                                                            @RequestParam(required = false) Long xCoordinateLesserThan,
                                                            @RequestParam(required = false) Long xCoordinateEquals,
                                                            @RequestParam(required = false) Integer yCoordinateGreaterThan,
                                                            @RequestParam(required = false) Integer yCoordinateLesserThan,
                                                            @RequestParam(required = false) Integer yCoordinateEquals,
                                                            @RequestParam(required = false) Long healthGreaterThan,
                                                            @RequestParam(required = false) Long healthLesserThan,
                                                            @RequestParam(required = false) Long healthEquals,
                                                            @RequestParam(required = false) Integer heartCountGreaterThan,
                                                            @RequestParam(required = false) Integer heartCountLesserThan,
                                                            @RequestParam(required = false) Integer heartCountEquals,
                                                            @RequestParam(required = false) Boolean loyal,
                                                            @RequestParam(required = false) MeleeWeapon meleeWeapon,
                                                            @RequestParam(required = false) String chapterName,
                                                            @RequestParam(required = false) String chapterWorld){
        return ResponseEntity.ok(SpaceMarinePageDTO.create(service.getByFilter(pageable, id, name, createdBefore, createdAfter,
                xCoordinateGreaterThan, xCoordinateLesserThan, xCoordinateEquals,
                yCoordinateGreaterThan, yCoordinateLesserThan, yCoordinateEquals,
                healthGreaterThan, healthLesserThan, healthEquals, heartCountGreaterThan,
                heartCountLesserThan, heartCountEquals, loyal, meleeWeapon, chapterName,
                chapterWorld)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws EntityNotFoundException {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody SpaceMarineUpdateDto updateDto) throws EntityNotFoundException, BadParams {
        service.updateById(updateDto);
    }

    @GetMapping("/group-by-name")
    public ResponseEntity<List<NameGroup>> groupByName(){
        return ResponseEntity.ok(service.groupByName());
    }

    @GetMapping("/max-healed")
    public ResponseEntity<SpaceMarineDTO> getWithMaxHealth(){
        return ResponseEntity.ok(SpaceMarineDTO.create(service.getWithMaxHealth()));
    }
}
