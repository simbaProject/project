package com.projet.simba.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Categorie {
  @Id
  @GeneratedValue(
          strategy = GenerationType.UUID
  )
  private UUID id;
  @Column(
          unique = true,
          nullable = false
  )
  private String libelle;
}