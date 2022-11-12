package com.example.stellantis.controllers;

import com.example.stellantis.dtos.RegionalDto;
import com.example.stellantis.dtos.VehicleDto;
import com.example.stellantis.models.Regional;
import com.example.stellantis.models.Vehicle;
import com.example.stellantis.repositories.RegionalRepository;
import com.example.stellantis.repositories.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/regionals")
public class RegionalController {

    @Autowired
    private RegionalRepository regionalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RegionalDto> all(){
        return regionalRepository
                .findAll()
                .stream()
                .map(r -> modelMapper.map(r, RegionalDto.class))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegionalDto create(@Valid @RequestBody RegionalDto request){
        Regional regional = modelMapper.map(request, Regional.class);
        Regional saved = regionalRepository.save(regional);

        return modelMapper.map(saved, RegionalDto.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegionalDto update(@PathVariable("id") Long id, @Valid @RequestBody RegionalDto request){

        Optional<Regional> optionalRegional = regionalRepository.findById(id);

        if (optionalRegional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Regional not found");
        }

        Regional regionalToBeUpdated = modelMapper.map(request, Regional.class);
        regionalToBeUpdated.setId(id);

        Regional regional = regionalRepository.save(regionalToBeUpdated);

        return modelMapper.map(regional, RegionalDto.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        Optional<Regional> optionalRegional = regionalRepository.findById(id);

        if (optionalRegional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Regional not found");
        }

        regionalRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}