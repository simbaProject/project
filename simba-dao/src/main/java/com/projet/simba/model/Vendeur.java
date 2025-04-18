package com.projet.simba.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Vendeur extends Users {

   
    @OneToMany(mappedBy = "vendeur", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Produits> listProduits= new ArrayList<>();
    private Double noteMoyenne;

    @OneToMany(mappedBy = "vendeur", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Note> notesList;

}
