package com.projet.simba.mapper;

import com.projet.simba.dto.CategorieDto;
import com.projet.simba.model.Categorie;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class CategorieMapperImpl implements CategorieMapper {

    @Override
    public CategorieDto toDto(Categorie categorie) {
        if ( categorie == null ) {
            return null;
        }

        CategorieDto categorieDto = new CategorieDto();

        categorieDto.setId( categorie.getId() );
        categorieDto.setLibelle( categorie.getLibelle() );

        return categorieDto;
    }

    @Override
    public Categorie toEntity(CategorieDto categorieDto) {
        if ( categorieDto == null ) {
            return null;
        }

        Categorie categorie = new Categorie();

        categorie.setLibelle( categorieDto.getLibelle() );

        return categorie;
    }
}
