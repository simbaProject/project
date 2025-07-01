package com.projet.simba.service;

import com.projet.simba.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UserDto updateUserMail(UUID id, UserDto userDto);

    void updateUserPassWord(UUID id, UserDto userDto, String oldPassWord);

    UserDto updateMontantCompte(UUID id, UserDto userDto);

    UserDto updateLocation(UUID id, UserDto userDto);

    UserDto updateCoordonnees(UUID id, UserDto userDto);

    UserDto getUser(UUID id);
}
