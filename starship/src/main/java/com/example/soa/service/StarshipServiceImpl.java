package com.example.soa.service;

import com.example.soa.model.dto.SpaceMarineDTO;
import com.example.soa.model.dto.Starship;
import com.example.soa.service.exceptions.EntityNotFoundException;
import com.example.soa.service.exceptions.SpaceMarineAlreadyLoaded;
import com.example.soa.service.exceptions.SpaceMarineDoesNotExist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StarshipServiceImpl implements StarshipService {

    private final StarshipFactory factory;
    private final StarshipRepository repository;

    @Override
    public void loadSpaceMarine(Long shipId, Long marineId) throws EntityNotFoundException, SpaceMarineAlreadyLoaded, SpaceMarineDoesNotExist {
        if (!checkIfSpaceMarinePresent(marineId)) throw new SpaceMarineDoesNotExist();
        Starship starship = repository.findById(shipId).orElseThrow(EntityNotFoundException::new);
        if (starship.getSpaceMarines().add(marineId)) repository.saveAndFlush(starship);
        else throw new SpaceMarineAlreadyLoaded();
    }

    @Override
    public void unloadAll(Long id) throws EntityNotFoundException {
        Starship starship = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        starship.getSpaceMarines().clear();
        repository.saveAndFlush(starship);
    }

    @Override
    public void create() {
        Starship starship = factory.create();
        repository.saveAndFlush(starship);
    }

    @Override
    public void delete(Long shipId) throws EntityNotFoundException {
        Starship starship = repository.findById(shipId).orElseThrow(EntityNotFoundException::new);
        repository.delete(starship);
    }

    @Override
    public List<Starship> getAll() {
        return repository.findAll();
    }

    private boolean checkIfSpaceMarinePresent(Long id){
        RestTemplate restTemplate = new RestTemplate();
        try{
        	ResponseEntity<Object> response = restTemplate.getForEntity("http://localhost:12444/space-marines/" + id, Object.class);
        	return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpServerErrorException ex) {
        	return false;
        }
    }

}
