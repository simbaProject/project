package com.projet.simba.repository;

import com.projet.simba.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContratRepository extends JpaRepository<Contrat, UUID> {
  Optional<Contrat> findByRefContratAndDeleteAtIsNull(String refContrat);

  List<Contrat> findByDeleteAtIsNull();
}