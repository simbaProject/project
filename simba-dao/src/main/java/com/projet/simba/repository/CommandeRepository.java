package com.projet.simba.repository;

import com.projet.simba.model.Commande;
import com.projet.simba.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommandeRepository extends JpaRepository<Commande, UUID> {

    Optional<Commande> findByIdAndDeleteAtIsNull(UUID uuid);
    List<Commande> findByDeleteAtIsNull();



    List<Commande> findByUserAndDeleteAtNull(Users user);
}
