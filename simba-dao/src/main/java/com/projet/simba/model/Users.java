package com.projet.simba.model;

import com.projet.simba.model.enumType.RoleUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID id;
    @Column(
            nullable = false
    )
    protected String nom;
    @Column(
            nullable = false
    )
    protected String motDePasse;
    @Enumerated(EnumType.STRING)
    private RoleUser role;
    @Column(
            unique = true,
            nullable = false
    )
    protected String adresseMail;
    @Column(
            nullable = false
    )
    protected String localisation;
    protected double montantCompte;
    @CreationTimestamp
    @Column(
            nullable = false,
            updatable = false
    )
    protected LocalDateTime createAt;
    @Column(
            columnDefinition = "geography(Point,4326)"
    )
    @JdbcTypeCode(3250)
    @Transient
    private Point geography;
    @UpdateTimestamp
    protected LocalDateTime updateAt;
    protected LocalDateTime deleteAt;
}
