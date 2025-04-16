package com.projet.simba.mapper;

import com.projet.simba.dto.VendeurDto;
import com.projet.simba.model.Vendeur;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProduitMapper.class, PointMapper.class})
public interface VendeurMapper {



    @Mapping(source = "geography", target = "latitude", qualifiedByName = "extractLatitude")
    @Mapping(source = "geography", target = "longitude", qualifiedByName = "extractLongitude")
    @Mapping(source = "listProduits", target = "listProduits")
    VendeurDto toDto(Vendeur vendeur);

    @Mapping(target = "geography", expression = "java(pointMapper.createPoint(dto.getLongitude(), dto.getLatitude()))")
    @Mapping(source = "listProduits", target = "listProduits")
    Vendeur toEntity(VendeurDto dto, @Context PointMapper pointMapper);
}
