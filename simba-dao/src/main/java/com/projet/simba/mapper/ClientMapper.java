package com.projet.simba.mapper;

import com.projet.simba.dto.ClientDto;
import com.projet.simba.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {PointMapper.class})
public interface ClientMapper {
    @Mapping(target = "latitude", source = "geography", qualifiedByName = "extractLatitude")
    @Mapping(target = "longitude", source = "geography", qualifiedByName = "extractLongitude")
    ClientDto toDto(Client client);

    @Mapping(source = "id" ,target = "id" ,ignore = true)
    @Mapping(source = "updateAt" ,target = "updateAt",ignore = true)
    @Mapping(source = "createAt" ,target = "createAt",ignore = true)
    Client toEntity(ClientDto clientDto );
}
