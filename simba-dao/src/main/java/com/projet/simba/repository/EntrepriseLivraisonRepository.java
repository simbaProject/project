package com.projet.simba.repository;

import com.projet.simba.model.Contrat;
import com.projet.simba.model.EntrepriseLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface EntrepriseLivraisonRepository extends JpaRepository<EntrepriseLivraison, UUID> {

    Optional<EntrepriseLivraison> findByIdAndDeleteAtIsNull(UUID uuid);

    @Query("select e from EntrepriseLivraison e where e.contrat = ?1 and e.deleteAt is null")
    Optional<EntrepriseLivraison> findByContratAndDeleteAtNull(Contrat contrat);

}