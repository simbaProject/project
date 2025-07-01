package com.projet.simba.repository;

import com.projet.simba.model.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface MultimediaRepository extends JpaRepository<Multimedia, UUID> {
    @Query("select m from Multimedia m where m.multimedia.cheminVersImage = ?1 and m.multimedia.deleteAt is null")
    Optional<Multimedia> findByMultimedia_CheminVersImageAndMultimedia_DeleteAtNull(String cheminVersImage);
    Optional<Multimedia> findByIdMuuidAndMultimedia_DeleteAtNull(UUID idMuuid);


}