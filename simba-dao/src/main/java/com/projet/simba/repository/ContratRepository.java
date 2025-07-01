package com.projet.simba.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import com.projet.simba.model.Contrat;



public interface ContratRepository extends  JpaRepository<Contrat ,UUID>{

    Optional<Contrat> findByRefContratAndDeleteAtIsNull(String refContrat);

    List<Contrat> findByDeleteAtIsNull();


    


}
