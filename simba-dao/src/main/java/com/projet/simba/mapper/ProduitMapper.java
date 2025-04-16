package com.projet.simba.mapper;

import com.projet.simba.dto.ProduitsDto;
import com.projet.simba.model.Produits;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    ProduitsDto toDto(Produits produits);

    Produits toEntity(ProduitsDto produitsDto);

    default List<UUID> mapProduitsToIds(List<Produits> produits) {
        if (produits == null) return null;
        return produits.stream()
                .map(Produits::getId)
                .collect(Collectors.toList());
    }

    default List<Produits> mapIdsToProduits(List<UUID> ids) {
        if (ids == null) return null;
        return ids.stream().map(id -> {
            Produits produit = new Produits();
            produit.setId(id);
            return produit;
        }).collect(Collectors.toList());
    }
}
