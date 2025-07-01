package com.projet.simba.service.impl;

import com.projet.simba.mapper.NoteMapper;
import com.projet.simba.model.Note;
import com.projet.simba.model.UserCanMark;
import com.projet.simba.model.Vendeur;
import com.projet.simba.repository.NoteRepository;
import com.projet.simba.repository.UserCanMarkRepository;
import com.projet.simba.repository.VendeurRepository;
import com.projet.simba.service.NoteService;
import com.projet.simba.dto.NoteDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final UserCanMarkRepository userRepository;
    private final VendeurRepository vendeurRepository;
    private final NoteMapper noteMapper;
    private final NoteRepository noteRepository;

    @Override
    public NoteDto createNote(NoteDto noteDto, UUID userId, UUID prestataireId) {
        Optional<UserCanMark> optionalUsers=userRepository.findByIdAndDeleteAtIsNull(userId);
        Optional<Vendeur> optionalVendeur =vendeurRepository.findByIdAndDeleteAtIsNull(prestataireId);
        if(optionalVendeur.isPresent() && optionalUsers.isPresent()){
            UserCanMark users=optionalUsers.get();
            Vendeur vendeur = optionalVendeur.get();

            Note note=noteMapper.toEntity(noteDto);
            note.setUser(users);
            note.setVendeur(vendeur);
            note=noteRepository.save(note);
            double noteMoyenne=noteRepository.calculNoteMoyen(note.getVendeur().getId());
            vendeur.setNoteMoyenne(noteMoyenne);
            vendeurRepository.save(vendeur);
            return noteMapper.toDto(note);

        }
        throw new IllegalArgumentException("Vendeur ou user non trouvé");
    }

    @Override
    public NoteDto getNote(UUID id) {
        Optional<Note> optionalNote=noteRepository.findByIdAndDeleteAtIsNull(id);
        return optionalNote.map(noteMapper::toDto).orElseThrow();
    }

    @Override
    public List<NoteDto> getNotes(UUID prestataire_id) {
        Optional<Vendeur> optionalVendeur =vendeurRepository.findByIdAndDeleteAtIsNull(prestataire_id);
        if(optionalVendeur.isPresent()) {
            List<Note> noteList = noteRepository.findByVendeurAndDeleteAtIsNull(optionalVendeur.get());
            return noteList.stream()
                    .map(noteMapper::toDto)
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException("Vendeur non trouvé");
    }

    @Override
    public List<NoteDto> getNotesByUser(UUID user_id) {
        Optional<UserCanMark> optionalUsers=userRepository.findByIdAndDeleteAtIsNull(user_id);
        if(optionalUsers.isPresent()){
            List<Note> noteList=noteRepository.findByUserAndDeleteAtIsNull(optionalUsers.get());
            return noteList.stream()
                    .map(noteMapper::toDto)
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException("Utilisateur non trouvé");
    }

    @Override
    public NoteDto updateAvis(UUID id, NoteDto noteDto) {
        Optional<Note> optionalNote=noteRepository.findByIdAndDeleteAtIsNull(id);
        if(optionalNote.isPresent()){
            Note note= optionalNote.get();
            note.setAvis(noteDto.getAvis());
            return noteMapper.toDto(noteRepository.save(note));
        }
        throw new IllegalArgumentException("note introuvable");
    }
}
