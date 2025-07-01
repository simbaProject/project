package com.projet.simba.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.projet.simba.model.enumType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idMuuid;

    @ManyToOne
    @JoinColumn(name = "produit_id",nullable = false)
    private Multimedia multimedia;

    @Column(nullable = false)
    private TypeMultimedia type;

    @Column(nullable = false)
    private String cheminVersImage;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;



}
