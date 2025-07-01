package com.projet.simba.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class ContratDto {
    private @NotNull(
            message = "L'admin doit entrer la reference du contrat "
    ) String refContrat;
    private @NotNull(
            message = "the price fixed is mandatory"
    ) double prixLivraison;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
