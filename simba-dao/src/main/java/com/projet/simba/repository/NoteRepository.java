package com.projet.simba.repository;

import com.projet.simba.model.Note;
import com.projet.simba.model.Users;
import com.projet.simba.model.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    Optional<Note> findByIdAndDeleteAtIsNull(UUID uuid);

    List<Note> findByDeleteAtIsNull();

    @Query("SELECT avg(note) from Note n where n.vendeur.id = :id")
    float calculNoteMoyen(@Param("id") UUID id);

    List<Note> findByUserAndDeleteAtIsNull(Users users);

    List<Note> findByVendeurAndDeleteAtIsNull(Vendeur vendeur);
}