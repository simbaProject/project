package com.projet.simba.repository;

import java.util.List;
//import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.simba.model.CommandeProduit;
import com.projet.simba.model.Produits;

public interface CommandeProduitRepository extends JpaRepository<CommandeProduit , UUID>{

    List<CommandeProduit> findByProduit(Produits produit);

}
