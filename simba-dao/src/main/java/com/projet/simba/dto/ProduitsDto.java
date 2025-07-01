package com.projet.simba.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class ProduitsDto {
    private UUID id;
    private @NotNull(
            message = "the libelle is mandatory"
    ) @NotEmpty(
            message = "the libelle can't be empty"
    ) String libelle;
    private @NotNull(
            message = "the stock's quantity is mandatory"
    ) @PositiveOrZero(
            message = "le prix unitaire ne peut etre negatif"
    ) int quantiteStock;
    private @NotNull(
            message = "the unit's price is mandatory"
    ) @PositiveOrZero(
            message = "le prix unitaire ne peut etre negatif"
    ) int prixUnitaire;
    private List<UUID> mediaList;
    private String categorieLib;
    private UUID vendeurId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
