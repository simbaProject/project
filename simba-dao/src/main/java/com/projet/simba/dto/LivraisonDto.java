package com.projet.simba.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.projet.simba.model.enumType.LivraisonState;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class LivraisonDto {
    private UUID id;
    private @NotNull(
            message = "Time of livraison is mandatory"
    ) float timeLivraison;
    private LivraisonState etatLivraison;
    private @NotNull(
            message = "phone of livreur is mandatory"
    ) @Size(
            min = 8,
            max = 25,
            message = "the phone number must be respect the format of cameroon's phone number"
    ) String numeroLivreur;
    private UUID commandeId;
}
