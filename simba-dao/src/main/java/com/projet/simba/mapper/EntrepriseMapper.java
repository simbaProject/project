package com.projet.simba.mapper;

import com.projet.simba.dto.EntrepriseLivraisonDto;
import com.projet.simba.model.EntrepriseLivraison;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" ,uses = {ContratMapper.class})
public interface EntrepriseMapper {

    @Mapping(source = "contrat",target = "refContrat" ,qualifiedByName = "mapContratToRefContrat")
    EntrepriseLivraisonDto toDto(EntrepriseLivraison entrepriseLivraison);

    @Mapping(source = "updateAt" ,target = "updateAt",ignore = true)
    @Mapping(source = "createAt" ,target = "createAt",ignore = true)
    @Mapping(source = "id",target = "id",ignore = true)
    EntrepriseLivraison toEntity(EntrepriseLivraisonDto entrepriseLivraisonDto);
}
