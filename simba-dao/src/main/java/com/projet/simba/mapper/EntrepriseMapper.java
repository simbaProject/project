package com.projet.simba.mapper;

import com.projet.simba.dto.EntrepriseLivraisonDto;
import com.projet.simba.model.EntrepriseLivraison;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" ,uses = {ContratMapper.class , PointMapper.class})
public interface EntrepriseMapper {

    @Mapping(target = "latitude", source = "geography", qualifiedByName = "extractLatitude")
    @Mapping(target = "longitude", source = "geography", qualifiedByName = "extractLongitude")
    @Mapping(source = "contrat",target = "refContrat" ,qualifiedByName = "mapContratToRefContrat")
    EntrepriseLivraisonDto toDto(EntrepriseLivraison entrepriseLivraison);

    @Mapping(source = "updateAt" ,target = "updateAt",ignore = true)
    @Mapping(source = "createAt" ,target = "createAt",ignore = true)
    @Mapping(source = "id",target = "id",ignore = true)
    @Mapping(target = "geography", expression = "java(pointMapper.createPoint(entrepriseLivraisonDto.getLongitude(), entrepriseLivraisonDto.getLatitude()))")
    EntrepriseLivraison toEntity(EntrepriseLivraisonDto entrepriseLivraisonDto, @Context PointMapper pointMapper);
}
