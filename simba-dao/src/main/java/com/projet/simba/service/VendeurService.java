package com.projet.simba.service;

import com.projet.simba.dto.VendeurDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * The interface vendeur service.
 */
@Service
public interface VendeurService {
    /**
     * Create vendeur  dto.
     *
     * @param vendeurDto the vendeur
     * @return the vendeur dto
     */
    VendeurDto createVendeur(VendeurDto vendeurDto);

    /**
     * Gets vendeur.
     *
     * @return the vendeur object dto
     */
    List<VendeurDto> getVendeurs();

    /**
     * Gets vendeurs.
     *
     * @param id the id
     * @return the prestataire
     */
    VendeurDto getUser(UUID id);

    /**
     * Gets vendeur.
     *
     * @param nom the nom
     * @return the vendeur
     */
    VendeurDto getUser(String nom);

    /**
     * Update vendeur vendeur dto.
     *
     * @param id             the id
     * @param vendeurDto the vendeur dto
     * @return the vendeur dto
     */
    VendeurDto updateVendeur(UUID id, VendeurDto vendeurDto);

    /**
     * Update vendeur name vendeur dto.
     *
     * @param id             the id
     * @param vendeurDto the vendeur dto
     * @return the vendeur dto
     */
    VendeurDto updateVendeurName(UUID id,VendeurDto vendeurDto);


    /**
     * Delete vendeur .
     *
     * @param id the id
     * @return the boolean result of this deleting
     */
    boolean deleteVendeur(UUID id);

    /**
     * Gets vendeurs by product. we want to give a list of sellers who has this product
     *
     * @param libelle the libelle of product
     * @return the vendeur by food
     */
    List<VendeurDto> getVendeursByProduct(String libelle);


}