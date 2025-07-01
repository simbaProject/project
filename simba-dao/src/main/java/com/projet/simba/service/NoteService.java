package com.projet.simba.service;

import com.projet.simba.dto.NoteDto;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    NoteDto createNote(NoteDto noteDto, UUID userId, UUID prestataireId);

    NoteDto getNote(UUID id);

    List<NoteDto> getNotes(UUID prestataire_id);

    List<NoteDto> getNotesByUser(UUID user_id);

    NoteDto updateAvis(UUID id, NoteDto noteDto);
}
