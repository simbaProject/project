package com.projet.simba.mapper;

import com.projet.simba.dto.EntrepriseLivraisonDto;
import com.projet.simba.model.EntrepriseLivraison;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class EntrepriseMapperImpl implements EntrepriseMapper {

    @Autowired
    private ContratMapper contratMapper;
    @Autowired
    private PointMapper pointMapper;

    @Override
    public EntrepriseLivraisonDto toDto(EntrepriseLivraison entrepriseLivraison) {
        if ( entrepriseLivraison == null ) {
            return null;
        }

        EntrepriseLivraisonDto entrepriseLivraisonDto = new EntrepriseLivraisonDto();

        entrepriseLivraisonDto.setLatitude( pointMapper.extractLatitude( entrepriseLivraison.getGeography() ) );
        entrepriseLivraisonDto.setLongitude( pointMapper.extractLongitude( entrepriseLivraison.getGeography() ) );
        entrepriseLivraisonDto.setRefContrat( contratMapper.mapContratToRefContrat( entrepriseLivraison.getContrat() ) );
        entrepriseLivraisonDto.setId( entrepriseLivraison.getId() );
        entrepriseLivraisonDto.setNom( entrepriseLivraison.getNom() );
        entrepriseLivraisonDto.setMotDePasse( entrepriseLivraison.getMotDePasse() );
        entrepriseLivraisonDto.setLocalisation( entrepriseLivraison.getLocalisation() );
        entrepriseLivraisonDto.setAdresseMail( entrepriseLivraison.getAdresseMail() );
        entrepriseLivraisonDto.setCreateAt( entrepriseLivraison.getCreateAt() );
        entrepriseLivraisonDto.setUpdateAt( entrepriseLivraison.getUpdateAt() );
        entrepriseLivraisonDto.setRole( entrepriseLivraison.getRole() );
        entrepriseLivraisonDto.setMontantCompte( entrepriseLivraison.getMontantCompte() );

        return entrepriseLivraisonDto;
    }

    @Override
    public EntrepriseLivraison toEntity(EntrepriseLivraisonDto entrepriseLivraisonDto, PointMapper pointMapper) {
        if ( entrepriseLivraisonDto == null ) {
            return null;
        }

        EntrepriseLivraison entrepriseLivraison = new EntrepriseLivraison();

        entrepriseLivraison.setNom( entrepriseLivraisonDto.getNom() );
        entrepriseLivraison.setMotDePasse( entrepriseLivraisonDto.getMotDePasse() );
        entrepriseLivraison.setRole( entrepriseLivraisonDto.getRole() );
        entrepriseLivraison.setAdresseMail( entrepriseLivraisonDto.getAdresseMail() );
        entrepriseLivraison.setLocalisation( entrepriseLivraisonDto.getLocalisation() );
        entrepriseLivraison.setMontantCompte( entrepriseLivraisonDto.getMontantCompte() );

        entrepriseLivraison.setGeography( pointMapper.createPoint(entrepriseLivraisonDto.getLongitude(), entrepriseLivraisonDto.getLatitude()) );

        return entrepriseLivraison;
    }
}
