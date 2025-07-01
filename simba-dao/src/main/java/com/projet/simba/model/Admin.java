package com.projet.simba.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
public class Admin extends UserCanMark {

    private UUID EntryKey;


}
