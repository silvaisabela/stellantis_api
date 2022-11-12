package com.example.stellantis.controllers;

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
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private RegionalRepository regionalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleDto> all(){
        return vehicleRepository
                .findAll()
                .stream()
                .map(v -> modelMapper.map(v, VehicleDto.class))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleDto create(@Valid @RequestBody VehicleDto request){

        Optional<Regional> optionalRegional = regionalRepository.findById(request.getRegionalId());

        if (optionalRegional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Regional not found");
        }

        Vehicle vehicle = modelMapper.map(request, Vehicle.class);
        Vehicle saved = vehicleRepository.save(vehicle);

        Regional regional = optionalRegional.get();

        regional.getVehicles().add(vehicle);
        regionalRepository.save(regional);

        return modelMapper.map(saved, VehicleDto.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleDto update(@PathVariable("id") Long id, @Valid @RequestBody VehicleDto request){

        if (request.getRegionalId() != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "is not possible to change vehicle from regional");
        }

        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if (optionalVehicle.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }

        Vehicle vehicleToBeUpdated = modelMapper.map(request, Vehicle.class);
        vehicleToBeUpdated.setId(id);

        Vehicle vehicle = vehicleRepository.save(vehicleToBeUpdated);

        return modelMapper.map(vehicle, VehicleDto.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if (optionalVehicle.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }

        vehicleRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}