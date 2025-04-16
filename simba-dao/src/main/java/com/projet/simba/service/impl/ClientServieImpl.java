package com.projet.simba.service.impl;

import com.projet.simba.dto.ClientDto;
import com.projet.simba.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServieImpl implements ClientService {
    /**
     * @param clientDto the client dto 
     * @return des client information
     */
    @Override
    public ClientDto createClient(ClientDto clientDto) {
        return null;
    }

    /**
     * @return  the client information
     */
    @Override
    public List<ClientDto> getClients() {
        return List.of();
    }

    /**
     * @param id the id 
     * @return the client information
     */
    @Override
    public ClientDto getClient(UUID id) {
        return null;
    }

    /**
     * @param nom the nom 
     * @return the client information
     */
    @Override
    public ClientDto getClient(String nom) {
        return null;
    }

    /**
     * @param clientDto the client dto 
     * @param client_id the client id
     * @return
     */
    @Override
    public ClientDto updateClient(ClientDto clientDto, UUID client_id) {
        return null;
    }

    /**
     * @param client_id the client id 
     * @param clientDto the client dto
     * @return
     */
    @Override
    public ClientDto updateAdresseMail(UUID client_id, ClientDto clientDto) {
        return null;
    }

    /**
     * @param client_id the client id 
     * @param clientDto the client dto
     * @return
     */
    @Override
    public ClientDto updateAdresse(UUID client_id, ClientDto clientDto) {
        return null;
    }

    /**
     * @param client_id the client id 
     * @param clientDto the client dto
     * @return
     */
    @Override
    public ClientDto updateDateOfBirth(UUID client_id, ClientDto clientDto) {
        return null;
    }

    /**
     * @param id the id 
     * @return
     */
    @Override
    public boolean deleteClient(UUID id) {
        return false;
    }
}
