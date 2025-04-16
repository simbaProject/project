package com.projet.simba.mapper;

import com.projet.simba.dto.NoteDto;
import com.projet.simba.model.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    Note toEntity(NoteDto noteDto);

    NoteDto toDto(Note note);
}
