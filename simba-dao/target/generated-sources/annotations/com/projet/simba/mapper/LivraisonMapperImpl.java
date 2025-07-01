package com.projet.simba.mapper;

import com.projet.simba.dto.LivraisonDto;
import com.projet.simba.model.Livraison;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class LivraisonMapperImpl implements LivraisonMapper {

    @Override
    public Livraison toEntity(LivraisonDto livraison) {
        if ( livraison == null ) {
            return null;
        }

        Livraison livraison1 = new Livraison();

        livraison1.setTimeLivraison( livraison.getTimeLivraison() );
        livraison1.setEtatLivraison( livraison.getEtatLivraison() );
        livraison1.setNumeroLivreur( livraison.getNumeroLivreur() );

        return livraison1;
    }

    @Override
    public LivraisonDto toDto(Livraison livraison) {
        if ( livraison == null ) {
            return null;
        }

        LivraisonDto livraisonDto = new LivraisonDto();

        livraisonDto.setId( livraison.getId() );
        livraisonDto.setTimeLivraison( livraison.getTimeLivraison() );
        livraisonDto.setEtatLivraison( livraison.getEtatLivraison() );
        livraisonDto.setNumeroLivreur( livraison.getNumeroLivreur() );

        return livraisonDto;
    }
}
