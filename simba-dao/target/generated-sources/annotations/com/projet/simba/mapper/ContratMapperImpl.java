package com.projet.simba.mapper;

import com.projet.simba.dto.ContratDto;
import com.projet.simba.model.Contrat;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class ContratMapperImpl implements ContratMapper {

    @Override
    public ContratDto toDto(Contrat contrat) {
        if ( contrat == null ) {
            return null;
        }

        ContratDto contratDto = new ContratDto();

        contratDto.setRefContrat( contrat.getRefContrat() );
        contratDto.setPrixLivraison( contrat.getPrixLivraison() );
        contratDto.setCreateAt( contrat.getCreateAt() );
        contratDto.setUpdateAt( contrat.getUpdateAt() );

        return contratDto;
    }

    @Override
    public Contrat toEntity(ContratDto contratDto) {
        if ( contratDto == null ) {
            return null;
        }

        Contrat contrat = new Contrat();

        contrat.setRefContrat( contratDto.getRefContrat() );
        contrat.setPrixLivraison( contratDto.getPrixLivraison() );

        return contrat;
    }
}
