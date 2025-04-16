package com.projet.simba.model;

import com.projet.simba.model.enumType.RoleUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    protected String nom;
    @Column(nullable = false)
    protected String motDePasse;
    @Enumerated(EnumType.STRING)
    private RoleUser role;
    @Column(nullable = false,unique = true)
    protected String adresseMail;

    @Column(nullable = false)
    protected String localisation;
    protected double montantCompte;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    protected LocalDateTime createAt;

    @UpdateTimestamp
    protected LocalDateTime updateAt;
    protected LocalDateTime deleteAt;



}
