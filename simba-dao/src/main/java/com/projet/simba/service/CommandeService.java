package com.projet.simba.service;

import com.projet.simba.dto.CommandeDto;

import java.util.List;
import java.util.UUID;

public interface CommandeService {
    CommandeDto createCommande(CommandeDto commandeDto, UUID user_id, UUID prestataireId);

    CommandeDto getCommande(UUID id);

    List<CommandeDto> getCommandes(UUID idPrestataire);

    List<CommandeDto> getCommandesByUser(UUID user_id);

    CommandeDto updateEtatCommande(UUID commande_id, CommandeDto commandeDto);

    CommandeDto updatePanier(UUID id, CommandeDto commandeDto);

    boolean deleteCommande(UUID id);
}
