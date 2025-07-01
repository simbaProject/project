package com.projet.simba.service;


import com.projet.simba.dto.EntrepriseLivraisonDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface EnterpriseLivraisonService {
    EntrepriseLivraisonDto createEnterprise(EntrepriseLivraisonDto entrepriseLivraisonDto);
    EntrepriseLivraisonDto getEnterprise(UUID id);
    List<EntrepriseLivraisonDto> getEnterprises();
    EntrepriseLivraisonDto getEnterprise(String nom);
    EntrepriseLivraisonDto updateEnterprise(UUID id,EntrepriseLivraisonDto entrepriseLivraisonDto);
    boolean deleteEnterprise(UUID id);
}
