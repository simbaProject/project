package com.projet.simba.mapper;

import com.projet.simba.dto.CategorieDto;
import com.projet.simba.model.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CategorieMapper {

    CategorieDto toDto(Categorie categorie);

    @Mapping(source= "id" ,target = "id", ignore = true)
    Categorie toEntity(CategorieDto categorieDto);

    @Named("mapCategorieToLibelle")
    default String mapCategorieToLibelle(Categorie categorie){
        return categorie.getLibelle().toUpperCase();
    }


}
