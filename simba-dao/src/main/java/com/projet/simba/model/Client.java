package com.projet.simba.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Client extends UserCanMark {
    private LocalDate dateOfBirth;
    private char genre;
}
