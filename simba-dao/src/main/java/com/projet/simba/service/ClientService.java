package com.projet.simba.service;

import java.util.List;
import java.util.UUID;
import com.projet.simba.dto.ClientDto;

public interface ClientService {
    ClientDto createClient(ClientDto clientDto);

    List<ClientDto> getClients();

    ClientDto getClient(UUID id);

    List<ClientDto> getClient(String nom);

    ClientDto updateClient(ClientDto clientDto, UUID client_id);

    ClientDto updateDateOfBirth(UUID client_id, ClientDto clientDto);

    boolean deleteClient(UUID id);
}
