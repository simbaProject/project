package com.projet.simba.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vendeur")
public class Vendeur  extends Users {
  @OneToMany(mappedBy = "vendeur", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Produits> listProduits = new ArrayList<>();
  private Double noteMoyenne;
  @OneToMany(
          mappedBy = "vendeur",
          cascade = {CascadeType.ALL},
          orphanRemoval = true
  )
  private List<Note> notesList;
}