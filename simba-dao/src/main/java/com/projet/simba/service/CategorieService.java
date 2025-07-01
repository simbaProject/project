package com.projet.simba.service;

import com.projet.simba.dto.CategorieDto;

import java.util.List;
import java.util.UUID;

public interface CategorieService {
    CategorieDto createCateg(CategorieDto categorieDto);

    List<CategorieDto> getAllCategorie();

    CategorieDto updateCategorie(CategorieDto categorieDto, UUID id);
}
