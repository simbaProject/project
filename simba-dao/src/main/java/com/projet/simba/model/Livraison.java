package com.projet.simba.model;

import com.projet.simba.model.enumType.LivraisonState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Livraison {
  @Id
  @GeneratedValue(
          strategy = GenerationType.UUID
  )
  private UUID id;
  private float timeLivraison;
  private LivraisonState etatLivraison;
  private String numeroLivreur;
  @CreationTimestamp
  @Column(
          nullable = false,
          updatable = false
  )
  private LocalDateTime createAt;
  @UpdateTimestamp
  private LocalDateTime updateAt;
  private LocalDateTime deleteAt;
  @ManyToOne(optional = false)
  private Commande commande;
}