package com.projet.simba.service;

import com.projet.simba.dto.ProduitsDto;
import com.projet.simba.model.Vendeur;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ProduitsService {
    ProduitsDto createProduct(ProduitsDto produitDto, UUID prestataire_id);

    @Transactional(readOnly = true)
    List<ProduitsDto> getProducts(UUID prestataire_id);

    @Transactional(readOnly = true)
    List<ProduitsDto> getProducts();

    @Transactional(readOnly = true)
    ProduitsDto getProduct(UUID id);

    @Transactional(readOnly = true)
    ProduitsDto getProduct(String nom, UUID prestataire_id);

    ProduitsDto updateProduct(UUID id, ProduitsDto produitAlimentaireDto);

    List<ProduitsDto> getCorbeille(UUID prestataire_id);

    ProduitsDto updateQuantiteStock(UUID produit_id, ProduitsDto produitsDto);

    void markAsProductDeleted(Vendeur vendeur);

    boolean deleteProduct(UUID id);
}
