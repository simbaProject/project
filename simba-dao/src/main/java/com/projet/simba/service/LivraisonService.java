package com.projet.simba.service;

import com.projet.simba.dto.LivraisonDto;

import java.util.UUID;

public interface LivraisonService {
    LivraisonDto createLivraison(LivraisonDto livraisonDto, UUID commandeId);

    LivraisonDto getLivraison(UUID id);

    LivraisonDto getLivraisonByCommande(UUID commande_id);

    LivraisonDto updateLivraison(UUID id, LivraisonDto livraisonDto);

    LivraisonDto updateEtat(UUID id, LivraisonDto livraisonDto);

    boolean deleteLivraison(UUID id);
}
