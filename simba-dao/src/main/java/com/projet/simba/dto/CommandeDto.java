package com.projet.simba.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.projet.simba.model.enumType.Etat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class CommandeDto {
    private UUID id;
    private Double prixTotal;
    private @NotNull(
            message = "you should to tell if the command is with livraison"
    ) boolean withLivraison;
    private UUID idSeller;
    private Map<UUID, Integer> productQuantite;
    private Etat etat;
    private LocalDateTime date;
}
