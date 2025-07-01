package com.projet.simba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class UserCanMark extends Users{
}