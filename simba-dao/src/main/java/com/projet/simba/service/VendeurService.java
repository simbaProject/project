package com.projet.simba.service;

import com.projet.simba.dto.VendeurDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The interface Prestataire service.
 */
@Service
public interface VendeurService {
    /**
     * Create prestataire prestataire dto.
     *
     * @param prestataireDto the prestataire dto
     * @return the prestataire dto
     */
    VendeurDto createVendeur(VendeurDto prestataireDto);

    /**
     * Gets prestataires.
     *
     * @return the prestataires
     */
    List<VendeurDto> getVendeurs();

    /**
     * Gets prestataire.
     *
     * @param id the id
     * @return the prestataire
     */
    VendeurDto getVendeur(UUID id);

    /**
     * Gets prestataire.
     *
     * @param nom the nom
     * @return the prestataire
     */
    VendeurDto getVendeur(String nom);

    /**
     * Update prestataire prestataire dto.
     *
     * @param id             the id
     * @param prestataireDto the prestataire dto
     * @return the prestataire dto
     */
    VendeurDto updateVendeur(UUID id, VendeurDto prestataireDto);

    /**
     * Update prestataire name prestataire dto.
     *
     * @param id             the id
     * @param prestataireDto the prestataire dto
     * @return the prestataire dto
     */
    VendeurDto updateVendeurName(UUID id,VendeurDto prestataireDto);

    /**
     * Update prestataire mail prestataire dto.
     *
     * @param id             the id
     * @param prestataireDto the prestataire dto
     * @return the prestataire dto
     */
    VendeurDto updateVendeurMail(UUID id,VendeurDto prestataireDto);

    /**
     * Update prestataire coordonnees prestataire dto.
     *
     * @param id             the id
     * @param prestataireDto the prestataire dto
     * @return the prestataire dto
     */
    VendeurDto updateVendeurCoordonnees(UUID id,VendeurDto prestataireDto);

    /**
     * Delete prestataire boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteVendeur(UUID id);

    /**
     * Update adresse vendeur dto.
     *
     * @param prestataireId  the prestataire id
     * @param prestataireDto the prestataire dto
     * @return the vendeur dto
     */
    VendeurDto updateAdresse(UUID prestataireId,VendeurDto prestataireDto);

    /**
     * Gets prestataires by food.
     *
     * @param libelle the libelle
     * @return the prestataires by food
     */
    List<VendeurDto> getVendeursByFood(String libelle);

    /**
     * Update prestataire nature vendeur dto.
     *
     * @param mail           the mail
     * @param prestataireDto the prestataire dto
     * @return the vendeur dto
     */
    VendeurDto updateVendeurNature(String mail, VendeurDto prestataireDto);
}