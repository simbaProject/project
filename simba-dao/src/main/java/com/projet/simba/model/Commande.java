package com.projet.simba.model;

import com.projet.simba.model.enumType.Etat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Etat etat;
    private Double prixTotal;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Produits> produits;

    @OneToMany(mappedBy = "commande",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CommandeProduit> commandeProduits=new ArrayList<>();


    private boolean withLivraison;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;


}
