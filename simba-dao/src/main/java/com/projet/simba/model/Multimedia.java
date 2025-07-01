package com.projet.simba.model;

import com.projet.simba.model.enumType.TypeMultimedia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Multimedia {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID id;

    @Column(
            nullable = false
    )
    private TypeMultimedia type;
    @Column(
            nullable = false
    )
    private String cheminVersImage;
    @CreationTimestamp
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produits_id")
    private Produits produits;

}