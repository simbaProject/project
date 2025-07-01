package com.projet.simba.service.impl;

import com.projet.simba.dto.EntrepriseLivraisonDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.mapper.EntrepriseMapper;
import com.projet.simba.mapper.PointMapper;
import com.projet.simba.model.Contrat;
import com.projet.simba.model.EntrepriseLivraison;
import com.projet.simba.model.Users;
import com.projet.simba.model.enumType.RoleUser;
import com.projet.simba.repository.ContratRepository;
import com.projet.simba.repository.EntrepriseLivraisonRepository;
import com.projet.simba.repository.UserRepository;
import com.projet.simba.service.EnterpriseLivraisonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.projet.simba.service.impl.VendeurServiceImpl.getErrorModels;

@Service
@AllArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseLivraisonService {
    private final EntrepriseLivraisonRepository entrepriseLivraisonRepository;
    private final EntrepriseMapper entrepriseMapper;
    private final ContratRepository contratRepository;
    private final UserRepository userRepository;
    private final PointMapper pointMapper;

    @Override
    public EntrepriseLivraisonDto createEnterprise(EntrepriseLivraisonDto entrepriseLivraisonDto) {
        Optional<Users> optionalUsers=userRepository.findByAdresseMailAndDeleteAtNull(entrepriseLivraisonDto.getAdresseMail());
        boolean userNotExists= optionalUsers.isEmpty();

        if(isNotReserved(entrepriseLivraisonDto.getNom().toUpperCase()) && userNotExists){
            entrepriseLivraisonDto.setNom(entrepriseLivraisonDto.getNom().toUpperCase());
            entrepriseLivraisonDto.setRole(RoleUser.ENTREPRISE_LIVRAISON);
            entrepriseLivraisonDto.setMontantCompte(0.0);
            Optional<Contrat> optionalContrat=contratRepository.findByRefContratAndDeleteAtIsNull(entrepriseLivraisonDto.getRefContrat());
            if(optionalContrat.isPresent()){
                Contrat contrat=optionalContrat.get();
                EntrepriseLivraison entrepriseLivraison=entrepriseMapper.toEntity(entrepriseLivraisonDto,pointMapper);
                entrepriseLivraison.setContrat(contrat);
                return entrepriseMapper.toDto(entrepriseLivraisonRepository.save(entrepriseLivraison));
            }
            else
                throw new IllegalArgumentException("contrat non existant pour cette reference");
        }
        else{

            List<ErrorModel> errorModelList = getErrorModels(userNotExists);
            throw new BusinessException(errorModelList , HttpStatus.FORBIDDEN);
        }


    }

    @Override
    public EntrepriseLivraisonDto getEnterprise(UUID id) {
        Optional<EntrepriseLivraison> optionalEntrepriseLivraison=entrepriseLivraisonRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalEntrepriseLivraison.isPresent()){
            EntrepriseLivraison entrepriseLivraison= optionalEntrepriseLivraison.get();
            return entrepriseMapper.toDto(entrepriseLivraison);
        }
        else
            throw new IllegalArgumentException("entreprise de livraison introuvable");
    }

    @Override
    public List<EntrepriseLivraisonDto> getEnterprises() {
        List<EntrepriseLivraison> entrepriseLivraisonList= entrepriseLivraisonRepository.findByDeleteAtIsNull();
        return entrepriseLivraisonList.stream()
                .map(entrepriseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EntrepriseLivraisonDto getEnterprise(String nom) {
        Optional<EntrepriseLivraison> optionalEntrepriseLivraison=entrepriseLivraisonRepository.findByNomAndDeleteAtIsNull(nom);
        if(optionalEntrepriseLivraison.isPresent()){
            EntrepriseLivraison entrepriseLivraison= optionalEntrepriseLivraison.get();
            return entrepriseMapper.toDto(entrepriseLivraison);
        }
        else
            throw new IllegalArgumentException("entreprise de livraison introuvable");
    }

    @Override
    public EntrepriseLivraisonDto updateEnterprise(UUID id, EntrepriseLivraisonDto entrepriseLivraisonDto) {
        Optional<Users> optionalUsers=userRepository.findByAdresseMailAndDeleteAtNull(entrepriseLivraisonDto.getAdresseMail());
        Optional<EntrepriseLivraison> optionalEntrepriseLivraison=entrepriseLivraisonRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalEntrepriseLivraison.isPresent()){
            EntrepriseLivraison entrepriseLivraison= optionalEntrepriseLivraison.get();
            if(optionalUsers.isEmpty()){
                entrepriseLivraison.setNom(entrepriseLivraisonDto.getNom().toUpperCase());
                return entrepriseMapper.toDto(entrepriseLivraisonRepository.save(entrepriseLivraison));
            }
            List<ErrorModel> errorModelList=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("OPERATION_DENIED");
            errorModel.setMessage("Ce nom est réservé");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList , HttpStatus.FORBIDDEN);

        }
        else
            throw new IllegalArgumentException("entreprise de livraison introuvable");
    }

    @Override
    public boolean deleteEnterprise(UUID id) {
        Optional<EntrepriseLivraison> optionalEntrepriseLivraison=entrepriseLivraisonRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalEntrepriseLivraison.isPresent()) {
            EntrepriseLivraison entrepriseLivraison = optionalEntrepriseLivraison.get();
            entrepriseLivraison.setDeleteAt(LocalDateTime.now());
            return true;
        }
        return false;
    }
    /**
     *
     * @param name of seller
     * @return if the name is reserved by other seller
     */

    private boolean  isNotReserved(String name){
        Optional<EntrepriseLivraison> optionalVendeur=entrepriseLivraisonRepository.findByNomAndDeleteAtIsNull(name);
        return optionalVendeur.isEmpty();

    }
}
