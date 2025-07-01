package com.projet.simba.repository;

import com.projet.simba.model.Produits;
import com.projet.simba.model.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProduitsRepository extends JpaRepository<Produits, UUID> {
    Optional<Produits> findByIdAndDeleteAtIsNull(UUID uuid);

    List<Produits> findByDeleteAtIsNull();

    List<Produits> findByVendeurAndDeleteAtIsNull(Vendeur vendeur);

    @Query("SELECT p FROM produits p WHERE p.vendeur.id = :vendeur_id AND p.deleteAt IS NULL AND p.libelle = :libelle ")
    Optional<Produits> findByVendeurOrderByLibelle(@Param("vendeur_id") UUID vendeur_id, @Param("libelle") String libelle);

    List<Produits> findByVendeurAndDeleteAtIsNotNull(Vendeur vendeur);
}