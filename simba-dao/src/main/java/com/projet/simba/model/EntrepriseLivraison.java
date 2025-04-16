package com.projet.simba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EntrepriseLivraison extends Users{

    @OneToOne(optional = false,orphanRemoval = true)
    @JoinColumn(name = "ref_contrat",updatable = false,unique = true)
    private Contrat contrat;

}
