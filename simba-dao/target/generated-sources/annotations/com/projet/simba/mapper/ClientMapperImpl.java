package com.projet.simba.mapper;

import com.projet.simba.dto.ClientDto;
import com.projet.simba.model.Client;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Autowired
    private PointMapper pointMapper;

    @Override
    public ClientDto toDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setLatitude( pointMapper.extractLatitude( client.getGeography() ) );
        clientDto.setLongitude( pointMapper.extractLongitude( client.getGeography() ) );
        clientDto.setId( client.getId() );
        clientDto.setNom( client.getNom() );
        clientDto.setMotDePasse( client.getMotDePasse() );
        clientDto.setLocalisation( client.getLocalisation() );
        clientDto.setAdresseMail( client.getAdresseMail() );
        clientDto.setCreateAt( client.getCreateAt() );
        clientDto.setUpdateAt( client.getUpdateAt() );
        clientDto.setRole( client.getRole() );
        clientDto.setMontantCompte( client.getMontantCompte() );
        clientDto.setDateOfBirth( client.getDateOfBirth() );
        clientDto.setGenre( client.getGenre() );

        return clientDto;
    }

    @Override
    public Client toEntity(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        Client client = new Client();

        client.setNom( clientDto.getNom() );
        client.setMotDePasse( clientDto.getMotDePasse() );
        client.setRole( clientDto.getRole() );
        client.setAdresseMail( clientDto.getAdresseMail() );
        client.setLocalisation( clientDto.getLocalisation() );
        client.setMontantCompte( clientDto.getMontantCompte() );
        client.setDateOfBirth( clientDto.getDateOfBirth() );
        client.setGenre( clientDto.getGenre() );

        return client;
    }
}
