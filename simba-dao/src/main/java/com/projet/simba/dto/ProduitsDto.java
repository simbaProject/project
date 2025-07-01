package com.projet.simba.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProduitsDto {

    private UUID id;
    @NotNull(message = "the libelle is mandatory")
    @NotEmpty(message = "the libelle can't be empty")
    private String libelle;
    @NotNull(message = "the stock's quantity is mandatory")
    private int quantiteStock;
    @NotNull(message = "the unit's price is mandatory")
    private int prixUnitaire;
    private List<UUID> mediaList;
    private String categorieLib;
    private UUID vendeurId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
