package com.projet.simba.service.impl;

import com.projet.simba.dto.ClientDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.mapper.ClientMapper;
import com.projet.simba.model.Client;
import com.projet.simba.model.Users;
import com.projet.simba.model.enumType.RoleUser;
import com.projet.simba.repository.ClientRepository;
import com.projet.simba.repository.UserRepository;
import com.projet.simba.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServieImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final UserRepository userRepository;


    /**
     * @param clientDto the client dto 
     * @return des client information
     */
    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Optional<Users> optionalUsers=userRepository.findByAdresseMailAndDeleteAtNull(clientDto.getAdresseMail());
        boolean userNotExits= optionalUsers.isEmpty();
        if(userNotExits){
            clientDto.setNom(clientDto.getNom().toUpperCase());
            clientDto.setRole(RoleUser.CLIENT);
            return  clientMapper.toDto(clientRepository.save(clientMapper.toEntity(clientDto)));
        }

        else{
            List<ErrorModel> errorModels=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("DOUBLE_PROFIL");
            errorModel.setMessage("un utilisateur avec cette addresse mail existe dejà");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels, HttpStatus.FORBIDDEN);
        }


    }

    /**
     * @return  the client information
     */
    @Override
    public List<ClientDto> getClients() {
        List<Client> clients=clientRepository.findByDeleteAtIsNull();

        return clients.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param id the id 
     * @return the client information
     */
    @Override
    public ClientDto getClient(UUID id) {
        Optional<Client> optionalClient=clientRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalClient.isPresent())
            return clientMapper.toDto(optionalClient.get());
        else
            throw new IllegalArgumentException("client doesn't exists");
    }

    /**
     * @param nom the nom 
     * @return the list of clients with this name
     */
    @Override
    public List<ClientDto> getClient(String nom) {
        List<Client> clients=clientRepository.findByNomAndDeleteAtIsNull(nom);
        return clients.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param clientDto the client dto 
     * @param client_id the client id
     * @return
     */
    @Override
    public ClientDto updateClient(ClientDto clientDto, UUID client_id) {
        Optional<Client> optionalClient=clientRepository.findByIdAndDeleteAtIsNull(client_id);
        if(optionalClient.isPresent()){

            Client client= optionalClient.get();
            client.setNom(clientDto.getNom().toUpperCase());
            client.setMontantCompte(clientDto.getMontantCompte());
            client.setDateOfBirth(clientDto.getDateOfBirth());
            client.setGenre(clientDto.getGenre());
            return clientMapper.toDto(clientRepository.save(client));
        }
        else
            throw new IllegalArgumentException("client doesn't exists");

    }




    /**
     * @param client_id the client id 
     * @param clientDto the client dto
     * @return
     */
    @Override
    public ClientDto updateDateOfBirth(UUID client_id, ClientDto clientDto) {
        Optional<Client> optionalClient=clientRepository.findByIdAndDeleteAtIsNull(client_id);
        if(optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setDateOfBirth(clientDto.getDateOfBirth());
            return clientMapper.toDto(clientRepository.save(client));
        }
        return null;
    }

    /**
     * @param id the id 
     * @return true if the client exists or
     */
    @Override
    public boolean deleteClient(UUID id) {
        Optional<Client> optionalClient=clientRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setDeleteAt(LocalDateTime.now());
            clientRepository.save(client);
            return true;
        }
        return false;
    }
}
