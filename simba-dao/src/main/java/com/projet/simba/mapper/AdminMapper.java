package com.projet.simba.mapper;

import com.projet.simba.dto.AdminDto;
import com.projet.simba.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses ={PointMapper.class})
public interface AdminMapper {
    @Mapping(target = "latitude", source = "geography", qualifiedByName = "extractLatitude")
    @Mapping(target = "longitude", source = "geography", qualifiedByName = "extractLongitude")
    AdminDto toDto(Admin admin);

    @Mapping(source = "id" ,target = "id" ,ignore = true)
    @Mapping(source = "updateAt" ,target = "updateAt",ignore = true)
    @Mapping(source = "createAt" ,target = "createAt",ignore = true)
    Admin toEntity(AdminDto adminDto);
}
