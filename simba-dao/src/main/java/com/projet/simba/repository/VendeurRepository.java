package com.projet.simba.repository;


import com.projet.simba.model.Vendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VendeurRepository extends JpaRepository<Vendeur, UUID> {

    Optional<Vendeur> findByIdAndDeleteAtIsNull(UUID uuid);
    List<Vendeur> findByDeleteAtIsNull();
    Optional<Vendeur> findByNomAndDeleteAtIsNull(String nom);
    @Query("SELECT p FROM Vendeur p JOIN p.listProduits pr WHERE pr.libelle = :libelle")
    List<Vendeur> findVendeursByProduitLibelle(@Param("libelle") String libelle);
    Optional<Vendeur> findByAdresseMailAndDeleteAtIsNull(String AdresseMail);

}
