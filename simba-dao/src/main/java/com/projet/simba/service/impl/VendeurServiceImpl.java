package com.projet.simba.service.impl;


import com.projet.simba.dto.VendeurDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.mapper.PointMapper;
import com.projet.simba.mapper.VendeurMapper;
import com.projet.simba.model.Users;
import com.projet.simba.model.Vendeur;
import com.projet.simba.model.enumType.RoleUser;
import com.projet.simba.repository.UserRepository;
import com.projet.simba.repository.VendeurRepository;
import com.projet.simba.service.ProduitsService;
import com.projet.simba.service.VendeurService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VendeurServiceImpl implements VendeurService {
    
    private final VendeurRepository vendeurRepository;
    private final VendeurMapper vendeurMapper;
    private final PointMapper pointMapper;
    private final UserRepository userRepository;
    private final ProduitsService produitsService;

    /**
     * @param vendeurDto the vendeur 
     * @return the dto object of vendeur
     */
    @Override
    public VendeurDto createVendeur(VendeurDto vendeurDto) {
        Optional<Users> optionalUsers=userRepository.findByAdresseMailAndDeleteAtNull(vendeurDto.getAdresseMail());
        boolean userNotExits= optionalUsers.isEmpty();
        if(isNotReserved(vendeurDto.getNom().toUpperCase()) && userNotExits){
            vendeurDto.setNoteMoyenne(0.0);
            vendeurDto.setMontantCompte(0.0);
            vendeurDto.setNom(vendeurDto.getNom().toUpperCase());
            vendeurDto.setListProduits(new ArrayList<>());
            vendeurDto.setRole(RoleUser.VENDEUR);
            Vendeur vendeur=vendeurMapper.toEntity(vendeurDto,pointMapper);
            return vendeurMapper.toDto(vendeurRepository.save(vendeur));
        }

        else{

            List<ErrorModel> errorModelList = getErrorModels(userNotExits);
            throw new BusinessException(errorModelList , HttpStatus.FORBIDDEN);
        }

    }

    public static List<ErrorModel> getErrorModels(boolean userExits) {
        List<ErrorModel> errorModelList=new ArrayList<>();
        ErrorModel errorModel = new ErrorModel();
        if(!userExits) {
            errorModel.setCode("DOUBLE_PROFIL");
            errorModel.setMessage("un utilisateur avec cette addresse mail existe dejà");
            errorModelList.add(errorModel);
        }
        else{
            ErrorModel errorModel1 = new ErrorModel();
            errorModel1.setCode("DOUBLE_PROFIL");
            errorModel1.setMessage("un utilisateur avec cette addresse mail existe dejà");
            errorModel.setCode("OPERATION_DENIED");
            errorModel.setMessage("Ce nom est réservé");
            errorModelList.add(errorModel);
            errorModelList.add(errorModel1);
        }
        return errorModelList;
    }

    /**
     * @return the list of vendeurs
     */
    @Transactional(readOnly = true)
    @Override
    public List<VendeurDto> getVendeurs() {
        List<Vendeur> vendeurs=vendeurRepository.findByDeleteAtIsNull();
        return vendeurs
                .stream()
                .map(vendeurMapper::toDto)
                .collect(Collectors.toList());
    }
    /**
     * @param id the id of vendeur
     * @return the dto's object of vendeur
     */
    @Override
    public VendeurDto getUser(UUID id) {
        Optional<Vendeur> optionalVendeur=vendeurRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalVendeur.isPresent()){
            Vendeur vendeur= optionalVendeur.get();
            return vendeurMapper.toDto(vendeur);
        }

        throw new IllegalArgumentException("the vendeur doesn't exists");
    }

    /**
     * @param nom the nom
     * @return the dto's object of vendeur
     */
    @Override
    public VendeurDto getUser(String nom) {
        Optional<Vendeur> optionalVendeur=vendeurRepository.findByNomAndDeleteAtIsNull(nom);
        if(optionalVendeur.isPresent()){
            Vendeur vendeur= optionalVendeur.get();
            return vendeurMapper.toDto(vendeur);
        }

        throw new IllegalArgumentException("the vendeur doesn't exists");
    }

    /**
     * @param id  the id
     * @param vendeurDto the vendeur dto
     * @return the dto object represents news information passed
     */
    @Override
    public VendeurDto updateVendeur(UUID id, VendeurDto vendeurDto) {

        Optional<Vendeur> optionalVendeur=vendeurRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalVendeur.isPresent()) {
            if(isNotReserved(vendeurDto.getNom().toUpperCase())){
                Vendeur vendeur= optionalVendeur.get();
                vendeur.setNom(vendeurDto.getNom().toUpperCase());
                vendeur.setLocalisation(vendeurDto.getLocalisation());
                vendeur.setGeography(pointMapper.createPoint(vendeurDto.getLongitude(), vendeurDto.getLatitude()));
                return vendeurMapper.toDto(vendeurRepository.save(vendeur));
            }
            else{
                List<ErrorModel> errorModelList=new ArrayList<>();
                ErrorModel errorModel=new ErrorModel();
                errorModel.setCode("OPERATION_DENIED");
                errorModel.setMessage("Ce nom est réservé");
                errorModelList.add(errorModel);
                throw new BusinessException(errorModelList , HttpStatus.FORBIDDEN);
            }

        }

        throw new IllegalArgumentException("the vendeur doesn't exists");
    }

    /**
     * @param id         the id 
     * @param vendeurDto the vendeur dto
     * @return the dto object represents news information passed
     */
    @Override
    public VendeurDto updateVendeurName(UUID id, VendeurDto vendeurDto) {
        Optional<Vendeur> optionalVendeur=vendeurRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalVendeur.isPresent()) {
            if(isNotReserved(vendeurDto.getNom().toUpperCase())){
                Vendeur vendeur= optionalVendeur.get();
                vendeur.setNom(vendeurDto.getNom());
                return vendeurMapper.toDto(vendeurRepository.save(vendeur));
            }
        }
        throw new IllegalArgumentException("the vendeur doesn't exists");
    }


    /**
     * @param id the id 
     * @return the dto object represents news information passed
     */
    @Override
    public boolean deleteVendeur(UUID id) {
        Optional<Vendeur> optionalVendeur=vendeurRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalVendeur.isPresent()) {
            Vendeur vendeur = optionalVendeur.get();
            vendeur.setDeleteAt(LocalDateTime.now());
            produitsService.markAsProductDeleted(vendeur);
            vendeurRepository.save(vendeur);
            return true;
        }
        return false;
    }


    /**
     * @param libelle the libelle 
     * @return the dto object represents news information passed
     */
    @Override
    public List<VendeurDto> getVendeursByProduct(String libelle) {
        List<Vendeur> vendeurs=vendeurRepository.findVendeursByProduitLibelle(libelle);
        return vendeurs
                .stream()
                .map(vendeurMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param name of seller
     * @return if the name is reserved by other seller
     */

    private boolean  isNotReserved(String name){
        Optional<Vendeur> optionalVendeur=vendeurRepository.findByNomAndDeleteAtIsNull(name);
        return optionalVendeur.isEmpty();

    }
}
