package com.example.stellantis.controllers;

import com.example.stellantis.dtos.EnvironmentDto;
import com.example.stellantis.dtos.VehicleDto;
import com.example.stellantis.models.Environment;
import com.example.stellantis.models.Vehicle;
import com.example.stellantis.repositories.EnvironmentRepository;
import com.example.stellantis.repositories.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehicles/{id}/environments")
public class EnvironmentController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EnvironmentRepository environmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<EnvironmentDto> allByVehicle(@PathVariable("id") Long id){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if (optionalVehicle.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }

        return optionalVehicle
                .get()
                .getEnvironments()
                .stream()
                .map(e -> modelMapper.map(e, EnvironmentDto.class))
                .toList();
    }

    @PostMapping
    public EnvironmentDto addToVehicle(@PathVariable("id") Long id, @Valid @RequestBody Environment body){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if (optionalVehicle.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }

        Vehicle vehicle = optionalVehicle.get();

        Environment environment = environmentRepository.save(modelMapper.map(body, Environment.class));

        vehicle.getEnvironments().add(environment);

        vehicleRepository.save(vehicle);

        return modelMapper.map(environment, EnvironmentDto.class);
    }
}
