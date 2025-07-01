package com.projet.simba.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
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
public class NoteDto {
    private UUID id;
    private @NotEmpty(
            message = "The avis is not empty. You can give a note without avis but don't show and empty advice"
    ) @Size(
            min = 4,
            message = "TThe avis has not than 4 characters . You can give a note without avis but don't show and empty advice"
    ) String avis;
    private @NotNull(
            message = "Note is mandatory"
    ) @PositiveOrZero(
            message = "la note ne peut etre negative"
    ) int note;
    private UUID userId;
    private UUID vendeurId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
