package com.projet.simba.mapper;

import com.projet.simba.dto.MultimediaDto;
import com.projet.simba.model.Multimedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MultimediaMapper {

    MultimediaDto toDto(Multimedia multimedia);

    @Mapping(source = "idMuuid" , target = "idMuuid",ignore = true)
    @Mapping(source = "updateAt" ,target = "updateAt",ignore = true)
    @Mapping(source = "createAt" ,target = "createAt",ignore = true)
    Multimedia toEntity(MultimediaDto multimediaDto);

    @Named("mapMediasToIds")
    default List<UUID> mapMediasToIds(List<Multimedia> multimediaList){
        if (multimediaList == null)
            return null;
        else
            return multimediaList
                    .stream()
                    .map(Multimedia::getIdMuuid)
                    .collect(Collectors.toList());
    }
}
