package com.projet.simba.service.impl;

import com.projet.simba.dto.CategorieDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.mapper.CategorieMapper;
import com.projet.simba.model.Categorie;

import com.projet.simba.repository.CategorieRepository;
import com.projet.simba.service.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieMapper categorieMapper;
    private final CategorieRepository categorieRepository;

    @Override
    public CategorieDto createCateg(CategorieDto categorieDto){
        Optional<Categorie> optionalCategorie=categorieRepository.findByLibelle(categorieDto.getLibelle().toUpperCase());
        if(optionalCategorie.isEmpty()){
            Categorie categorie=categorieMapper.toEntity(categorieDto);
            categorie.setLibelle(categorie.getLibelle().toUpperCase());
            return categorieMapper.toDto(categorieRepository.save(categorie));
        }
        else{
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_ACTION");
            errorModel.setMessage("Il est existe deja une categorie avec ce nom");
            throw new BusinessException(List.of(errorModel), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public List<CategorieDto> getAllCategorie(){
        List<Categorie> categorieList=categorieRepository.findAll();
        return categorieList.stream().map(categorieMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CategorieDto updateCategorie(CategorieDto categorieDto, UUID id){
        Optional<Categorie> optionalCategorie=categorieRepository.findById(id);
        if(optionalCategorie.isPresent()){
            Categorie categorie= optionalCategorie.get();
            categorie.setLibelle(categorieDto.getLibelle().toUpperCase());
            return categorieMapper.toDto(categorieRepository.save(categorie));
        }
        else
            throw new IllegalArgumentException("Categorie non trouvee");
    }



}
