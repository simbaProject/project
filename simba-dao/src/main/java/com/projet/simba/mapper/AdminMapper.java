package com.projet.simba.mapper;

import com.projet.simba.dto.AdminDto;
import com.projet.simba.model.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminDto toDto(Admin admin);

    Admin toEntity(AdminDto adminDto);
}
