package com.projet.simba.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.projet.simba.model.enumType.TypeMultimedia;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;



@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MultimediaDto {

    private UUID idMuuid;


    private MultimediaDto multimedia;


    private TypeMultimedia type;

    private String cheminVersImage;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;



}
