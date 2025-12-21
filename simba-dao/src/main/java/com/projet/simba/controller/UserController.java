package com.projet.simba.controller;


import com.projet.simba.dto.UserDto;
import com.projet.simba.mapper.UserMapper;
import com.projet.simba.model.Users;
import com.projet.simba.repository.UserRepository;
import com.projet.simba.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    @PostMapping("/add")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201" , description = "Utilisateur crée avec succès"),
            @ApiResponse(responseCode = "400" , description = "Mauvaises entrées d'information")
    }
    )
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user= userService.createUser(userDto);
        Users users=userMapper.toEntity(user);
        user=userMapper.toDto(userRepository.save(users));
        System.err.println(user.getId());
        return new ResponseEntity<>(user, HttpStatus.CREATED);


    }

}
