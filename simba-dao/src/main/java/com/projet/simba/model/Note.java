package com.projet.simba.model;

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
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String avis;
    private int note;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserCanMark user;

    @ManyToOne
    @JoinColumn(name = "vendeur_id",nullable = false)
    private Vendeur vendeur;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;



}
