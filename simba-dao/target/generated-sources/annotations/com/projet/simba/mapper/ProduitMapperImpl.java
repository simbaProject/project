package com.projet.simba.mapper;

import com.projet.simba.dto.ProduitsDto;
import com.projet.simba.model.Produits;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class ProduitMapperImpl implements ProduitMapper {

    @Autowired
    private MultimediaMapper multimediaMapper;
    @Autowired
    private CategorieMapper categorieMapper;

    @Override
    public ProduitsDto toDto(Produits produits) {
        if ( produits == null ) {
            return null;
        }

        ProduitsDto produitsDto = new ProduitsDto();

        produitsDto.setMediaList( multimediaMapper.mapMediasToIds( produits.getMultimedias() ) );
        produitsDto.setCategorieLib( categorieMapper.mapCategorieToLibelle( produits.getCategorie() ) );
        produitsDto.setId( produits.getId() );
        produitsDto.setLibelle( produits.getLibelle() );
        produitsDto.setQuantiteStock( produits.getQuantiteStock() );
        produitsDto.setPrixUnitaire( produits.getPrixUnitaire() );
        produitsDto.setCreateAt( produits.getCreateAt() );
        produitsDto.setUpdateAt( produits.getUpdateAt() );

        return produitsDto;
    }

    @Override
    public Produits toEntity(ProduitsDto produitsDto) {
        if ( produitsDto == null ) {
            return null;
        }

        Produits produits = new Produits();

        produits.setLibelle( produitsDto.getLibelle() );
        produits.setQuantiteStock( produitsDto.getQuantiteStock() );
        produits.setPrixUnitaire( produitsDto.getPrixUnitaire() );

        return produits;
    }
}
