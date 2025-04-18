package com.projet.simba.mapper;

import com.projet.simba.dto.CommandeDto;
import com.projet.simba.model.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    @Mapping(source = "date",target = "createAt",ignore = true)
    Commande toEntity(CommandeDto commandeDto);

    @Mapping(source = "createAt",target = "date")
    CommandeDto toDto(Commande commande);

    @Named("mapCommandeToId")
    private UUID mapCommandeToId(Commande commande){
        if (commande == null)return  null;
        else return commande.getId();
    }
}
