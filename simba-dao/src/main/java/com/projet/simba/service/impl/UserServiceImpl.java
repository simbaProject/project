package com.projet.simba.service.impl;

import com.projet.simba.dto.UserDto;
import com.projet.simba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;



    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setMotDePasse(passwordEncoder.encode(userDto.getMotDePasse()));
        userDto.setNom(userDto.getNom().toUpperCase());

        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDto getUser(UUID id) {
        return null;
    }

    @Override
    public UserDto updateUser(UUID id, UserDto userDto) {
        return null;
    }

    @Override
    public void updateUserPassWord(UUID id, UserDto userDto, String oldPassWord) {

    }

    @Override
    public boolean deleteUser(UUID id) {
        return false;
    }

    @Override
    public UserDto updateMontantCompte(UUID id, UserDto userDto) {
        return null;
    }
}
