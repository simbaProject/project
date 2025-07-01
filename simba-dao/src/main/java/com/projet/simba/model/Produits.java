package com.projet.simba.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Produits {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID id;
    private String libelle;
    @OneToMany(mappedBy = "produits", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Multimedia> multimedias = new ArrayList<>();
    private int quantiteStock;
    private int prixUnitaire;
    @ManyToOne
    @JoinColumn(
            name = "vendeur_id",
            nullable = false
    )
    private Vendeur vendeur;
    @ManyToOne(optional = false)
    private Categorie categorie;
    @CreationTimestamp
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

}