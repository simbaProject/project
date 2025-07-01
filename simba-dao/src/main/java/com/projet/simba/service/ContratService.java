package com.projet.simba.service;

import com.projet.simba.dto.ContratDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContratService {
    ContratDto createContract(ContratDto contratDto);
    List<ContratDto> getContracts();
    ContratDto getContract(String refContract);
    ContratDto updateContract(String refContract, ContratDto contratDto);
    boolean deleteContract(String refContract);
}
