package com.projet.simba.mapper;

import com.projet.simba.dto.EntrepriseLivraisonDto;
import com.projet.simba.model.EntrepriseLivraison;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntrepriseMapper {

    EntrepriseLivraisonDto toDto(EntrepriseLivraison entrepriseLivraison);

    EntrepriseLivraison toEntity(EntrepriseLivraisonDto entrepriseLivraisonDto);
}
