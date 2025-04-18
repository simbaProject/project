package com.projet.simba.mapper;

import com.projet.simba.dto.LivraisonDto;
import com.projet.simba.model.Livraison;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LivraisonMapper {
    @Mapping(source = "id", target = "id" ,ignore = true)
    Livraison toEntity(LivraisonDto livraison);


    LivraisonDto toDto(Livraison livraison);
}
