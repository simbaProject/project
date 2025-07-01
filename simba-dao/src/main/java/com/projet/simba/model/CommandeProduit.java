package com.projet.simba.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommandeProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produit_id",nullable = false)
    Produits produit;

    @ManyToOne
    @JoinColumn(name = "commande_id",nullable = false)
    Commande commande;

    int quantiteCommandee;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    


}
