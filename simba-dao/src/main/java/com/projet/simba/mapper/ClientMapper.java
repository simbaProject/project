package com.projet.simba.mapper;

import com.projet.simba.dto.ClientDto;
import com.projet.simba.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);
}
