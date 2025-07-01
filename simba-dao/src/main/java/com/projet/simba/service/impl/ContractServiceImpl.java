package com.projet.simba.service.impl;

import com.projet.simba.dto.ContratDto;
import com.projet.simba.mapper.ContratMapper;
import com.projet.simba.model.Contrat;
import com.projet.simba.repository.ContratRepository;
import com.projet.simba.service.ContratService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContratService {
    private final ContratRepository contratRepository;
    private final ContratMapper contratMapper;
    /**
     * @param contratDto 
     * @return
     */
    @Override
    public ContratDto createContract(ContratDto contratDto) {
        Contrat contrat=contratMapper.toEntity(contratDto);
        return contratMapper.toDto(contratRepository.save(contrat));
    }

    /**
     * @return 
     */
    @Override
    public List<ContratDto> getContracts() {
        List<Contrat> contratList=contratRepository.findByDeleteAtIsNull();

        return contratList
                .stream().map(contratMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param refContract 
     * @return
     */
    @Override
    public ContratDto getContract(String refContract) {
        Optional<Contrat> optionalContrat=contratRepository.findByRefContratAndDeleteAtIsNull(refContract);
        if(optionalContrat.isPresent()){
            Contrat contrat= optionalContrat.get();
            return contratMapper.toDto(contrat);
        }
        else
            throw new IllegalArgumentException("not found contract with  this reference");
    }

    /**
     * @param refContract 
     * @param contratDto
     * @return
     */
    @Override
    public ContratDto updateContract(String refContract, ContratDto contratDto) {
        Optional<Contrat> optionalContrat=contratRepository.findByRefContratAndDeleteAtIsNull(refContract);
        if(optionalContrat.isPresent()){
            Contrat contrat= optionalContrat.get();
            contrat.setPrixLivraison(contratDto.getPrixLivraison());
            return contratMapper.toDto(contratRepository.save(contrat));
        }
        else
            throw new IllegalArgumentException("not found contract with  this reference");

    }

    /**
     * @param refContract 
     * @return
     */
    @Override
    public boolean deleteContract(String refContract) {
        Optional<Contrat> optionalContrat=contratRepository.findByRefContratAndDeleteAtIsNull(refContract);
        if(optionalContrat.isPresent()) {
            Contrat contrat = optionalContrat.get();
            contrat.setDeleteAt(LocalDateTime.now());
            contratRepository.save(contrat);
        }
        return false;
    }
}
