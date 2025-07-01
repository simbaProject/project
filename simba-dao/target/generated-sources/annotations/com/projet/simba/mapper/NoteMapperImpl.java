package com.projet.simba.mapper;

import com.projet.simba.dto.NoteDto;
import com.projet.simba.model.Note;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Autowired
    private VendeurMapper vendeurMapper;

    @Override
    public Note toEntity(NoteDto noteDto) {
        if ( noteDto == null ) {
            return null;
        }

        Note note = new Note();

        note.setAvis( noteDto.getAvis() );
        note.setNote( noteDto.getNote() );

        return note;
    }

    @Override
    public NoteDto toDto(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteDto noteDto = new NoteDto();

        noteDto.setVendeurId( vendeurMapper.mapsVendeurToId( note.getVendeur() ) );
        noteDto.setId( note.getId() );
        noteDto.setAvis( note.getAvis() );
        noteDto.setNote( note.getNote() );
        noteDto.setCreateAt( note.getCreateAt() );
        noteDto.setUpdateAt( note.getUpdateAt() );

        return noteDto;
    }
}
