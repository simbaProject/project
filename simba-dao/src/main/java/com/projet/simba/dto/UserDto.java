package com.projet.simba.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.projet.simba.model.enumType.RoleUser;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class UserDto {
    protected UUID id;
    protected @NotNull(
            message = "The name is mandatory"
    ) String nom;
    protected @NotNull(
            message = "The password must be specified"
    ) @Size(
            min = 8,
            message = "The password has 8 characters minimum"
    ) String motDePasse;
    private Double longitude;
    private Double latitude;
    protected String localisation;
    protected @NotNull(
            message = "The mail address is mandatory"
    ) @Size(
            min = 1,
            max = 50,
            message = "The mail is between 1 to 50 characters"
    ) String adresseMail;
    protected LocalDateTime createAt;
    protected LocalDateTime updateAt;
    private RoleUser role;
    private @PositiveOrZero(
            message = "le montant ne peut etre negatif"
    ) double montantCompte;
}
