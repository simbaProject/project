package com.projet.simba.service.impl;

import com.projet.simba.dto.ProduitsDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.mapper.ProduitMapper;
import com.projet.simba.model.Categorie;
import com.projet.simba.model.Multimedia;
import com.projet.simba.model.Produits;
import com.projet.simba.model.Vendeur;
import com.projet.simba.repository.CategorieRepository;
import com.projet.simba.repository.MultimediaRepository;
import com.projet.simba.repository.ProduitsRepository;
import com.projet.simba.repository.VendeurRepository;
import com.projet.simba.service.ProduitsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitsService {

    private final ProduitsRepository produitsRepository;
    private final ProduitMapper produitMapper;
    private final CategorieRepository categorieRepository;
    private final MultimediaRepository multimediaRepository;
    private final VendeurRepository vendeurRepository;


    @Override
    public ProduitsDto createProduct(ProduitsDto produitDto, UUID prestataire_id) {
        Optional<Vendeur> optionalprestataire=vendeurRepository.findByIdAndDeleteAtIsNull(prestataire_id);
        Optional<Categorie> optionalCategorie=categorieRepository.findByLibelle(produitDto.getCategorieLib().toUpperCase());
        if(optionalprestataire.isPresent() && optionalCategorie.isPresent()){
            Vendeur prestataire= optionalprestataire.get();
            Categorie categorie= optionalCategorie.get();
            Optional<Produits> optionalProduits=produitsRepository.findByVendeurOrderByLibelle(prestataire_id, produitDto.getLibelle());
            if(optionalProduits.isEmpty()) {
                Produits produits = produitMapper.toEntity(produitDto);
                produits.setVendeur(prestataire);
                produits.setCategorie(categorie);
                produits=produitsRepository.save(produits);
                return produitMapper.toDto(produits);
            }
            else {
                List<ErrorModel> errorModels=new ArrayList<>();
                ErrorModel errorModel=new ErrorModel();
                errorModel.setCode("INVALID_ENTRY");
                errorModel.setMessage("Impossible d'entrer un meme libelle pour deux produits veuillez modifier la quantite en stocke et/ou d'autres caracteristiques du produit");
                errorModels.add(errorModel);
                throw new BusinessException(errorModels, HttpStatus.UNAUTHORIZED);
            }
        }
        else
            throw new IllegalArgumentException("Vendeur ou categorie de produit non specifié");
    }
    @Transactional(readOnly = true)
    @Override
    public List<ProduitsDto> getProducts(UUID prestataire_id) {
        Optional<Vendeur> optionalprestataire=vendeurRepository.findByIdAndDeleteAtIsNull(prestataire_id);
        if(optionalprestataire.isPresent()) {
            Vendeur prestataire = optionalprestataire.get();
            List<Produits> produitsList = produitsRepository.findByVendeurAndDeleteAtIsNull(prestataire);
            return produitsList.stream()
                    .map(produitMapper::toDto)
                    .collect(Collectors.toList());
        }
        else
            throw new IllegalArgumentException("Vendeur non trouvé");
    }

    /**
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<ProduitsDto> getProducts() {
        List<Produits> produitsList=produitsRepository.findByDeleteAtIsNull();
        return produitsList.stream()
                .map(produitMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public ProduitsDto getProduct(UUID id) {
        Optional<Produits> optionalProduits=produitsRepository.findByIdAndDeleteAtIsNull(id);
        return optionalProduits.map(produitMapper::toDto).orElse(null);
    }

    /**
     * @param nom
     * @param prestataire_id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public ProduitsDto getProduct(String nom, UUID prestataire_id) {
        Optional<Vendeur> optionalprestataire=vendeurRepository.findByIdAndDeleteAtIsNull(prestataire_id);
        if(optionalprestataire.isPresent()) {
            Optional<Produits> optionalProduits = produitsRepository.findByVendeurOrderByLibelle(prestataire_id, nom);
            return optionalProduits.map(produitMapper::toDto).orElse(null);
        }
        else
            throw new IllegalArgumentException("Vendeur non trouvé");
    }

    /**
     * @param id
     * @param produitAlimentaireDto
     * @return
     */
    @Override
    public ProduitsDto updateProduct(UUID id, ProduitsDto produitAlimentaireDto) {
        Optional<Produits> optionalProduits=produitsRepository.findByIdAndDeleteAtIsNull(id);
        if (optionalProduits.isPresent())
        {
            List<Multimedia> imagesList=new ArrayList<>();
            Produits produits= optionalProduits.get();
            produits.setLibelle(produitAlimentaireDto.getLibelle());
            produits.setQuantiteStock(produitAlimentaireDto.getQuantiteStock());
            if(!produitAlimentaireDto.getMediaList().isEmpty()) {
                for (UUID idImage : produitAlimentaireDto.getMediaList()) {
                    Optional<Multimedia> optionalImages = multimediaRepository.findByIdAndDeleteAtIsNull(idImage);
                    optionalImages.ifPresent(imagesList::add);
                }
            }
            produits.setMultimedias(imagesList);
            produits.setPrixUnitaire(produitAlimentaireDto.getPrixUnitaire());
            return produitMapper.toDto(produitsRepository.save(produits));
        }
        else
            throw new IllegalArgumentException("Produit non trouvé");
    }

    /**
     * @param prestataire_id
     * @return
     */
    @Override
    public List<ProduitsDto> getCorbeille(UUID prestataire_id) {
        Optional<Vendeur> optionalPrestataire=vendeurRepository.findByIdAndDeleteAtIsNull(prestataire_id);
        if(optionalPrestataire.isPresent())
        {
            List<Produits> produitsList=produitsRepository.findByVendeurAndDeleteAtIsNotNull(optionalPrestataire.get());
            return produitsList.stream()
                    .map(produitMapper::toDto)
                    .collect(Collectors.toList());
        }
        else
            throw new IllegalArgumentException("Vendeur non trouvé");
    }

    /**
     * @param produit_id
     * @param produitsDto
     * @return
     */
    @Override
    public ProduitsDto updateQuantiteStock(UUID produit_id, ProduitsDto produitsDto) {
        Optional<Produits> optionalProduits=produitsRepository.findByIdAndDeleteAtIsNull(produit_id);
        if(optionalProduits.isPresent()){
            Produits produits= optionalProduits.get();
            produits.setQuantiteStock(produitsDto.getQuantiteStock());
            return produitMapper.toDto(produitsRepository.save(produits));
        }
        else
            throw new IllegalArgumentException("Produit non trouvé");
    }

    /**
     * @param vendeur
     */
    @Override
    public void markAsProductDeleted(Vendeur vendeur) {
        List<Produits> produitsList=produitsRepository.findByVendeurAndDeleteAtIsNull(vendeur);
        for(Produits produits:produitsList){
            produits.setDeleteAt(LocalDateTime.now());
            produitsRepository.save(produits);
        }

    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteProduct(UUID id) {
        Optional<Produits> optionalProduits=produitsRepository.findByIdAndDeleteAtIsNull(id);
        if (optionalProduits.isPresent()) {
            Produits produits = optionalProduits.get();
            produits.setDeleteAt(LocalDateTime.now());
            produitsRepository.save(produits);
            return true;
        }
        return false;
    }
}
