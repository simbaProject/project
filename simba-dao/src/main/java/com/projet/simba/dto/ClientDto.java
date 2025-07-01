package com.projet.simba.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class ClientDto extends UserCanMarkDto {
    private @NotNull(
            message = "The date of birth is mandatory"
    ) LocalDate dateOfBirth;
    private @NotNull(
            message = "The sex must to be specified birth is mandatory"
    ) char genre;
}
