package com.example.stellantis.controllers;

import com.example.stellantis.dtos.UserDto;
import com.example.stellantis.dtos.VehicleDto;
import com.example.stellantis.models.Regional;
import com.example.stellantis.models.User;
import com.example.stellantis.models.Vehicle;
import com.example.stellantis.repositories.RegionalRepository;
import com.example.stellantis.repositories.UserRepository;
import com.example.stellantis.repositories.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody UserDto body){
        User user = modelMapper.map(body, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);

        return modelMapper.map(saved, UserDto.class);
    }
}