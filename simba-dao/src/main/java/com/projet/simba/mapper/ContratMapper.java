package com.projet.simba.mapper;

import com.projet.simba.dto.ContratDto;
import com.projet.simba.model.Contrat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ContratMapper {

    ContratDto toDto(Contrat contrat);


    @Mapping(source = "createAt" , target = "createAt", ignore = true)
    @Mapping(source = "updateAt" , target = "updateAt", ignore = true)
    Contrat toEntity(ContratDto contratDto);

    @Named("mapContratToRefContrat")
    default String mapContratToRefContrat(Contrat contrat){
        return contrat.getRefContrat();
    }


}
