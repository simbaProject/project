package com.projet.simba.mapper;

import com.projet.simba.dto.UserDto;
import com.projet.simba.model.Users;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private PointMapper pointMapper;

    @Override
    public Users toEntity(UserDto userDto, PointMapper pointMapper) {
        if ( userDto == null ) {
            return null;
        }

        Users users = new Users();

        users.setNom( userDto.getNom() );
        users.setMotDePasse( userDto.getMotDePasse() );
        users.setRole( userDto.getRole() );
        users.setAdresseMail( userDto.getAdresseMail() );
        users.setLocalisation( userDto.getLocalisation() );
        users.setMontantCompte( userDto.getMontantCompte() );

        users.setGeography( pointMapper.createPoint(userDto.getLongitude(), userDto.getLatitude()) );

        return users;
    }

    @Override
    public UserDto toDto(Users users) {
        if ( users == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setLatitude( pointMapper.extractLatitude( users.getGeography() ) );
        userDto.setLongitude( pointMapper.extractLongitude( users.getGeography() ) );
        userDto.setId( users.getId() );
        userDto.setNom( users.getNom() );
        userDto.setMotDePasse( users.getMotDePasse() );
        userDto.setLocalisation( users.getLocalisation() );
        userDto.setAdresseMail( users.getAdresseMail() );
        userDto.setCreateAt( users.getCreateAt() );
        userDto.setUpdateAt( users.getUpdateAt() );
        userDto.setRole( users.getRole() );
        userDto.setMontantCompte( users.getMontantCompte() );

        return userDto;
    }
}
