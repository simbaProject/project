package com.projet.simba.mapper;

import com.projet.simba.dto.NoteDto;
import com.projet.simba.model.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" ,uses = {VendeurMapper.class})
public interface NoteMapper {

    @Mapping(target = "vendeur", source = "vendeurId", ignore = true)
    @Mapping(source = "id" ,target = "id", ignore = true)
    @Mapping(source = "updateAt" ,target = "updateAt",ignore = true)
    @Mapping(source = "createAt" ,target = "createAt",ignore = true)
    Note toEntity(NoteDto noteDto);

    @Mapping(source = "vendeur" ,target = "vendeurId" ,qualifiedByName = "mapsVendeurToId")
    NoteDto toDto(Note note);
}
