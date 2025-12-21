package com.projet.simba.mapper;


import com.projet.simba.dto.UserDto;
import com.projet.simba.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel="spring")
public interface UserMapper {


     UserDto toDto(Users user);

     @Mapping(target = "id", source = "id", ignore = true)
     @Mapping(target = "updateAt", source = "updateAt",ignore = true)
     @Mapping(target = "createAt", source = "createAt", ignore = true)
     Users toEntity(UserDto userDto);
}
