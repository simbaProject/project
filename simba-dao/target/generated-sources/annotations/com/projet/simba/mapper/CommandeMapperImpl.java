package com.projet.simba.mapper;

import com.projet.simba.dto.CommandeDto;
import com.projet.simba.model.Commande;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class CommandeMapperImpl implements CommandeMapper {

    @Override
    public Commande toEntity(CommandeDto commandeDto) {
        if ( commandeDto == null ) {
            return null;
        }

        Commande commande = new Commande();

        commande.setId( commandeDto.getId() );
        commande.setEtat( commandeDto.getEtat() );
        commande.setPrixTotal( commandeDto.getPrixTotal() );
        commande.setWithLivraison( commandeDto.isWithLivraison() );

        return commande;
    }

    @Override
    public CommandeDto toDto(Commande commande) {
        if ( commande == null ) {
            return null;
        }

        CommandeDto commandeDto = new CommandeDto();

        commandeDto.setDate( commande.getCreateAt() );
        commandeDto.setId( commande.getId() );
        commandeDto.setPrixTotal( commande.getPrixTotal() );
        commandeDto.setWithLivraison( commande.isWithLivraison() );
        commandeDto.setEtat( commande.getEtat() );

        return commandeDto;
    }
}
