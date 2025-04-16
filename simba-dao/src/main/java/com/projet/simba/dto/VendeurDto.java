package com.projet.simba.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VendeurDto extends UserDto {

    private List<UUID> listProduits;
    private Double noteMoyenne;
    
    private Double longitude;
    private Double latitude;
   /* @NotNull(message = "the address is mandatory")
    private String address;*/
    private double montantCompte;

}
