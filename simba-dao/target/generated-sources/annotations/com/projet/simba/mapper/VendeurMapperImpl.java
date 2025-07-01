package com.projet.simba.mapper;

import com.projet.simba.dto.VendeurDto;
import com.projet.simba.model.Vendeur;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:36+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class VendeurMapperImpl implements VendeurMapper {

    @Autowired
    private PointMapper pointMapper;
    @Autowired
    private ProduitMapper produitMapper;

    @Override
    public VendeurDto toDto(Vendeur vendeur) {
        if ( vendeur == null ) {
            return null;
        }

        VendeurDto vendeurDto = new VendeurDto();

        vendeurDto.setLatitude( pointMapper.extractLatitude( vendeur.getGeography() ) );
        vendeurDto.setLongitude( pointMapper.extractLongitude( vendeur.getGeography() ) );
        vendeurDto.setListProduits( produitMapper.mapProduitsToIds( vendeur.getListProduits() ) );
        vendeurDto.setId( vendeur.getId() );
        vendeurDto.setNom( vendeur.getNom() );
        vendeurDto.setMotDePasse( vendeur.getMotDePasse() );
        vendeurDto.setLocalisation( vendeur.getLocalisation() );
        vendeurDto.setAdresseMail( vendeur.getAdresseMail() );
        vendeurDto.setCreateAt( vendeur.getCreateAt() );
        vendeurDto.setUpdateAt( vendeur.getUpdateAt() );
        vendeurDto.setRole( vendeur.getRole() );
        vendeurDto.setNoteMoyenne( vendeur.getNoteMoyenne() );
        vendeurDto.setMontantCompte( vendeur.getMontantCompte() );

        return vendeurDto;
    }

    @Override
    public Vendeur toEntity(VendeurDto vendeurDto, PointMapper pointMapper) {
        if ( vendeurDto == null ) {
            return null;
        }

        Vendeur vendeur = new Vendeur();

        vendeur.setNom( vendeurDto.getNom() );
        vendeur.setMotDePasse( vendeurDto.getMotDePasse() );
        vendeur.setRole( vendeurDto.getRole() );
        vendeur.setAdresseMail( vendeurDto.getAdresseMail() );
        vendeur.setLocalisation( vendeurDto.getLocalisation() );
        vendeur.setMontantCompte( vendeurDto.getMontantCompte() );
        vendeur.setNoteMoyenne( vendeurDto.getNoteMoyenne() );

        vendeur.setGeography( pointMapper.createPoint(vendeurDto.getLongitude(), vendeurDto.getLatitude()) );

        return vendeur;
    }
}
