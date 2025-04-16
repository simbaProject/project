package com.projet.simba.repository;

import com.projet.simba.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategorieRepository extends JpaRepository<Categorie, UUID> {

}