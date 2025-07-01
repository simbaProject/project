package com.projet.simba.mapper;

import com.projet.simba.dto.UserDto;
import com.projet.simba.model.Users;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {PointMapper.class})
public interface UserMapper {
    @Mapping(source = "updateAt" ,target = "updateAt",ignore = true)
    @Mapping(source = "createAt" ,target = "createAt",ignore = true)
    @Mapping(source = "id" ,target = "id",ignore = true)
    @Mapping(target = "geography", expression = "java(pointMapper.createPoint(userDto.getLongitude(), userDto.getLatitude()))")
    Users toEntity(UserDto userDto, @Context PointMapper pointMapper);

    @Mapping(target = "latitude", source = "geography", qualifiedByName = "extractLatitude")
    @Mapping(target = "longitude", source = "geography", qualifiedByName = "extractLongitude")
    UserDto toDto(Users users);
}
