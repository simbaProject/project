package com.projet.simba.mapper;

import com.projet.simba.dto.ProduitsDto;
import com.projet.simba.model.Produits;
import com.projet.simba.repository.ProduitsRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" , uses = {MultimediaMapper.class , CategorieMapper.class })
public interface ProduitMapper {

    @Mapping(source = "multimedias",target = "mediaList" , qualifiedByName = "mapMediasToIds")
    @Mapping(source = "categorie" ,target = "categorieLib" ,qualifiedByName = "mapCategorieToLibelle")

    ProduitsDto toDto(Produits produits);

    @Mapping(source = "id" ,target = "id" ,ignore = true)
    @Mapping(source = "updateAt" ,target = "updateAt",ignore = true)
    @Mapping(source = "createAt" ,target = "createAt",ignore = true)
    Produits toEntity(ProduitsDto produitsDto);

    @Named("mapProduitsToIds")
    default List<UUID> mapProduitsToIds(List<Produits> produits) {
        if (produits == null) return null;
        return produits.stream()
                .map(Produits::getId)
                .collect(Collectors.toList());
    }

   /* @Named("mapIdsToProduits")
    default List<Produits> mapIdsToProduits(List<UUID> ids, @Context ProduitsRepository produitRepository) {
        if (ids == null) return null;

        return ids.stream()
                .map(produitRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }*/
}
